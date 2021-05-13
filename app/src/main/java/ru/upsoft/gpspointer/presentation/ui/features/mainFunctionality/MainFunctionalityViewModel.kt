package ru.upsoft.gpspointer.presentation.ui.features.mainFunctionality

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
class MainFunctionalityViewModel @Inject constructor(
    private val locationUseCase: LocationUseCase,
    private val weatherUseCase: WeatherUseCase,
) : ViewModel() {

    val locationStateFlow = locationUseCase.locationStateFlow
    val weatherSharedFlow = weatherUseCase.weatherSharedFlow

    var location: Location? = null
    private var weatherMonitoringJob: Job? = null

    fun startLocationMonitoring() = viewModelScope.launch {
        locationUseCase.startLocationMonitoring()
    }

    fun stopLocationMonitoring() = viewModelScope.launch {
        locationUseCase.stopLocationMonitoring()
    }

    fun startWeatherMonitoring(location: Location) {
        if (weatherMonitoringJob != null) return
        weatherMonitoringJob = viewModelScope.launch(Dispatchers.IO) {
            this@MainFunctionalityViewModel.location = location
            while (isActive) {
                weatherUseCase.getWeather(location)
                delay(TimeUnit.MINUTES.toMillis(5))
            }
        }
    }

}