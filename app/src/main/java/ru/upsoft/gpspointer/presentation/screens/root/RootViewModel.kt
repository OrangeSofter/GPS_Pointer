package ru.upsoft.gpspointer.presentation.screens.root

import android.location.Location
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import ru.upsoft.gpspointer.domain.usecase.navigation.NavigationUseCase
import ru.upsoft.gpspointer.domain.usecase.weather.WeatherUseCase
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val navigationUseCase: NavigationUseCase,
    private val weatherUseCase: WeatherUseCase,
) : ViewModel() {

    val locationStateFlow = navigationUseCase.locationStateFlow
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
        navigationUseCase.startNavigationMonitoring()
    }

    fun stopLocationMonitoring() = viewModelScope.launch(Dispatchers.IO) {
        navigationUseCase.stopNavigationMonitoring()
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