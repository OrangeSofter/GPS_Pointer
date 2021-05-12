package ru.upsoft.gpspointer.data.repository

import retrofit2.Response
import ru.upsoft.gpspointer.data.network.model.WeatherNetworkModel
import ru.upsoft.gpspointer.data.network.weather.WeatherApi
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(latitude: Double, longitude: Double): Response<WeatherNetworkModel> {
        return weatherApi.getWeather(latitude, longitude)
    }


}