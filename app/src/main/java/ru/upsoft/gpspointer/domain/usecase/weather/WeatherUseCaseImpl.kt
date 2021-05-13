package ru.upsoft.gpspointer.domain.usecase.weather

import android.location.Location
import kotlinx.coroutines.flow.MutableSharedFlow
import ru.upsoft.gpspointer.core.model.WeatherState
import ru.upsoft.gpspointer.data.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
) : WeatherUseCase {

    override val weatherSharedFlow = MutableSharedFlow<WeatherState>()

    override suspend fun getWeather(location: Location) {
        val response = weatherRepository.getWeather(location.latitude, location.longitude)
        val data = response.body()
        when {
            response.isSuccessful && data != null ->
                weatherSharedFlow.emit(
                    WeatherState.Success(data)
                )
            else -> weatherSharedFlow.emit(
                WeatherState.Failed
            )
        }
    }

}