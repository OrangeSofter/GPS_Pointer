package ru.upsoft.gpspointer.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.upsoft.gpspointer.domain.usecase.location.LocationUseCase
import ru.upsoft.gpspointer.domain.usecase.location.LocationUseCaseImpl

@Module
@InstallIn(SingletonComponent::class)
interface UseCasesModule {

    @Binds
    fun locationUseCase(locationUseCase: LocationUseCaseImpl): LocationUseCase

}