package ru.upsoft.gpspointer.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.upsoft.gpspointer.data.repository.WeatherRepository
import ru.upsoft.gpspointer.data.repository.WeatherRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun weatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository


}