package ru.upsoft.gpspointer.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.upsoft.gpspointer.data.repository.CompassRepositoryImpl
import ru.upsoft.gpspointer.data.repository.GeoPointsRepositoryImpl
import ru.upsoft.gpspointer.data.repository.LocationRepositoryImpl
import ru.upsoft.gpspointer.data.repository.WeatherRepositoryImpl
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository
import ru.upsoft.gpspointer.domain.repository.LocationRepository
import ru.upsoft.gpspointer.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun weatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    fun locationRepository(locationRepository: LocationRepositoryImpl): LocationRepository

    @Binds
    @Singleton
    fun compassRepository(compassRepository: CompassRepositoryImpl): CompassRepository

    @Binds
    @Singleton
    fun geoPointRepository(geoPointRepository: GeoPointsRepositoryImpl): GeoPointsRepository


}