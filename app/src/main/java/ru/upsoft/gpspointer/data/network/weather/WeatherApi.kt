package ru.upsoft.gpspointer.data.network.weather

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.upsoft.gpspointer.data.network.model.WeatherNetworkModel

interface WeatherApi {

    @GET("${WeatherApiConstants.WEATHER_BY_COORDINATES_URL}?appid=${WeatherApiConstants.APP_ID}")
    fun getWeather(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Response<WeatherNetworkModel>

}