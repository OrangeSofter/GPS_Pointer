package ru.upsoft.gpspointer.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.upsoft.gpspointer.domain.usecase.navigation.NavigationUseCase
import ru.upsoft.gpspointer.domain.usecase.navigation.NavigationUseCaseImpl
import ru.upsoft.gpspointer.domain.usecase.weather.WeatherUseCase
import ru.upsoft.gpspointer.domain.usecase.weather.WeatherUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    fun locationUseCase(locationUseCase: NavigationUseCaseImpl): NavigationUseCase

    @Binds
    fun weatherUseCase(weatherUseCase: WeatherUseCaseImpl): WeatherUseCase

}