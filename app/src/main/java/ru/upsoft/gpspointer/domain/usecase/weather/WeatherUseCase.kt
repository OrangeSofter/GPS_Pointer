package ru.upsoft.gpspointer.domain.usecase.weather

import android.location.Location
import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.WeatherState

interface WeatherUseCase {

    val weatherStateFlow: StateFlow<WeatherState>

    suspend fun getWeather(location: Location)
}