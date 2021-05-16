package ru.upsoft.gpspointer.data.repository

import retrofit2.Call
import ru.upsoft.gpspointer.data.network.model.WeatherNetworkModel

interface WeatherRepository {

    suspend fun getWeather(latitude: Double, longitude: Double): Call<WeatherNetworkModel>

}