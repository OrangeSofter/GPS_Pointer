package ru.upsoft.gpspointer.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.upsoft.gpspointer.data.network.weather.WeatherApiConstants
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Named("WeatherRetrofit")
    fun provideWeatherRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(WeatherApiConstants.WEATHER_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}