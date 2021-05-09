package ru.upsoft.gpspointer.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.upsoft.gpspointer.data.repository.WeatherRepository
import ru.upsoft.gpspointer.data.repository.WeatherRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
 interface DataModule {

    @Binds
    fun provideWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository


}