package ru.upsoft.gpspointer.data.repository

import retrofit2.Call
import ru.upsoft.gpspointer.data.network.model.WeatherNetworkModel
import ru.upsoft.gpspointer.data.network.weather.WeatherApi
import ru.upsoft.gpspointer.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(latitude: Double, longitude: Double): Call<WeatherNetworkModel> {
        return weatherApi.getWeather(latitude, longitude)
    }

}