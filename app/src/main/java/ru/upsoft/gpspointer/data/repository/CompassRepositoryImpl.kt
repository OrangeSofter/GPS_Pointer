package ru.upsoft.gpspointer.data.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.upsoft.gpspointer.domain.model.CompassFailure
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import javax.inject.Inject

class CompassRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : CompassRepository {
    private val _compassStateFlow = MutableStateFlow<CompassState>(CompassState.Initial)

    override val compassStateFlow: StateFlow<CompassState> = _compassStateFlow.asStateFlow()

    private val coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val lock = Object()

    private var gravityParams = FloatArray(3)
    private var magneticParams = FloatArray(3)

    private var orientationParams = FloatArray(3)
    private var rotationMatrix = FloatArray(9)


    private val magneticSensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            magneticParams = event.values.clone()
            coroutineScope.launch { updateDegree() }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    }

    private val accelerometerSensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            gravityParams = event.values.clone()
            coroutineScope.launch { updateDegree() }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    }

    private fun updateDegree() = synchronized(lock) {
        SensorManager.getRotationMatrix(rotationMatrix, null, gravityParams, magneticParams)
        SensorManager.getOrientation(rotationMatrix, orientationParams)
        val degree = (orientationParams.first() * 180 / Math.PI).toFloat()
        _compassStateFlow.value = CompassState.Loaded(degree)
    }

    override fun startCompass() {
        val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) ?: run {
            _compassStateFlow.value = CompassState.Failed(CompassFailure.HAVE_NOT_SENSOR)
            return
        }
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) ?: run {
            _compassStateFlow.value = CompassState.Failed(CompassFailure.HAVE_NOT_SENSOR)
            return
        }
        sensorManager.registerListener(
            magneticSensorListener,
            magneticSensor,
            SensorManager.SENSOR_DELAY_GAME
        )
        sensorManager.registerListener(
            accelerometerSensorListener,
            accelerometerSensor,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun stopCompass() {
        sensorManager.unregisterListener(magneticSensorListener)
        sensorManager.unregisterListener(accelerometerSensorListener)
        _compassStateFlow.value = CompassState.Initial
    }
}