package ru.upsoft.gpspointer.presentation.screens.root

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import ru.upsoft.gpspointer.domain.usecase.location.LocationUseCase
import ru.upsoft.gpspointer.domain.usecase.weather.WeatherUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val weatherUseCase: WeatherUseCase,
) : ViewModel() {

    val locationStateFlow = locationUseCase.locationStateFlow
    val weatherStateFlow = weatherUseCase.weatherStateFlow

    private lateinit var location: Location
    private var weatherMonitoringJob: Job? = null

    fun onStart(){
        startLocationMonitoring()
    }

    fun onStop(){
        stopLocationMonitoring()
    }

    fun startLocationMonitoring() = viewModelScope.launch(Dispatchers.IO) {
        locationUseCase.startLocationMonitoring()
    }

    fun stopLocationMonitoring() = viewModelScope.launch(Dispatchers.IO) {
        locationUseCase.stopLocationMonitoring()
    }

    fun startWeatherMonitoring(newLocation: Location) {
        location = newLocation
        if (weatherMonitoringJob != null) return
        weatherMonitoringJob = viewModelScope.launch(Dispatchers.IO) {
            while (isActive) {
                weatherUseCase.getWeather(location)
                delay(TimeUnit.MINUTES.toMillis(5))
            }
        }
    }

}