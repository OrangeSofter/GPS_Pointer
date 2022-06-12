package ru.upsoft.gpspointer.domain.usecase.weather

import android.location.Location
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.awaitResponse
import ru.upsoft.gpspointer.domain.model.WeatherState
import ru.upsoft.gpspointer.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherUseCaseImpl @Inject constructor(
    private val weatherRepository: WeatherRepository
) : WeatherUseCase {

    override val weatherStateFlow = MutableStateFlow<WeatherState>(WeatherState.Loading)

    override suspend fun getWeather(location: Location) {
        val response = weatherRepository.getWeather(location.latitude, location.longitude).awaitResponse()
        val data = response.body()
        when {
            response.isSuccessful && data != null ->
                weatherStateFlow.emit(
                    WeatherState.Success(data)
                )
            else -> weatherStateFlow.emit(
                WeatherState.Failed
            )
        }
    }

}