package ru.upsoft.gpspointer.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import ru.upsoft.gpspointer.data.network.weather.WeatherApi
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    fun provideWeatherApi(@Named("WeatherRetrofit") retrofit: Retrofit): WeatherApi =
        retrofit.create(WeatherApi::class.java)


}