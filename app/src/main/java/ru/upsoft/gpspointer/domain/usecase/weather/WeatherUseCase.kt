package ru.upsoft.gpspointer.domain.usecase.weather

import android.location.Location
import kotlinx.coroutines.flow.SharedFlow
import ru.upsoft.gpspointer.core.model.WeatherState

interface WeatherUseCase {

    val weatherSharedFlow: SharedFlow<WeatherState>

    suspend fun getWeather(location: Location)
}