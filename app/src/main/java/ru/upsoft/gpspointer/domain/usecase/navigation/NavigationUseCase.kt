package ru.upsoft.gpspointer.domain.usecase.navigation

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.model.LocationState
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import ru.upsoft.gpspointer.domain.repository.LocationRepository
import javax.inject.Inject

interface NavigationUseCase {

    val locationStateFlow: StateFlow<LocationState>

    val compassStateFlow: StateFlow<CompassState>

    fun startNavigationMonitoring()

    fun stopNavigationMonitoring()

}

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