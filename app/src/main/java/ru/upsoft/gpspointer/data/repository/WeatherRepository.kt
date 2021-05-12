package ru.upsoft.gpspointer.data.repository

import retrofit2.Response
import ru.upsoft.gpspointer.data.network.model.WeatherNetworkModel

interface WeatherRepository {

    suspend fun getWeather(latitude: Double, longitude: Double): Response<WeatherNetworkModel>

}