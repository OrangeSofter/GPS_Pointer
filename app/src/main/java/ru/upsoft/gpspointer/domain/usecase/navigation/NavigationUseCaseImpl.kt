package ru.upsoft.gpspointer.domain.usecase.navigation

import ru.upsoft.gpspointer.domain.repository.LocationRepository
import javax.inject.Inject

class NavigationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository
) : NavigationUseCase {

    override val locationStateFlow = locationRepository.locationStateFlow

    override fun startLocationMonitoring() {
        locationRepository.startLocationMonitoring()
    }

    override fun stopLocationMonitoring() {
        locationRepository.stopLocationMonitoring()
    }
}