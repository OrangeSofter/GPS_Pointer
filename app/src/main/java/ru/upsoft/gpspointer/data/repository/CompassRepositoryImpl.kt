package ru.upsoft.gpspointer.data.repository

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.upsoft.gpspointer.domain.model.CompassFailure
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import javax.inject.Inject

class CompassRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : CompassRepository {
    private val _compassStateFlow = MutableStateFlow<CompassState>(CompassState.Initial)

    override val compassStateFlow: StateFlow<CompassState> = _compassStateFlow.asStateFlow()

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager


    private val orientationSensorListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            _compassStateFlow.value = CompassState.Loaded(event.values.first())
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}

    }

    override fun startCompass() {
        val orientationSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION) ?: run {
            _compassStateFlow.value = CompassState.Failed(CompassFailure.HAVE_NOT_SENSOR)
            return
        }
        sensorManager.registerListener(
            orientationSensorListener,
            orientationSensor,
            SensorManager.SENSOR_DELAY_GAME
        )
    }

    override fun stopCompass() {
        sensorManager.unregisterListener(orientationSensorListener)
        _compassStateFlow.value = CompassState.Initial
    }
}