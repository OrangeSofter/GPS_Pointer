package ru.upsoft.gpspointer.domain.usecase.navigation

import ru.upsoft.gpspointer.domain.repository.CompassRepository
import ru.upsoft.gpspointer.domain.repository.LocationRepository
import javax.inject.Inject

class NavigationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val compassRepository: CompassRepository
) : NavigationUseCase {

    override val locationStateFlow = locationRepository.locationStateFlow

    override val compassStateFlow = compassRepository.compassStateFlow

    override fun startNavigationMonitoring() {
        locationRepository.startLocationMonitoring()
        compassRepository.startCompass()
    }

    override fun stopNavigationMonitoring() {
        locationRepository.stopLocationMonitoring()
        compassRepository.stopCompass()
    }
}