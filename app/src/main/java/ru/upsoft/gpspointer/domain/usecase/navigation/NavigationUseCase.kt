package ru.upsoft.gpspointer.domain.usecase.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.LocationState
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository
import ru.upsoft.gpspointer.domain.repository.LocationRepository
import javax.inject.Inject

interface NavigationUseCase {

    val locationStateFlow: StateFlow<LocationState>

    val compassStateFlow: StateFlow<CompassState>

    val selectedPointState: StateFlow<GeoPoint?>

    fun startNavigationMonitoring()

    fun stopNavigationMonitoring()

    suspend fun onSelectPoint(pointName: String?)

}

class NavigationUseCaseImpl @Inject constructor(
    private val locationRepository: LocationRepository,
    private val compassRepository: CompassRepository,
    private val geoPointsRepository: GeoPointsRepository,
) : NavigationUseCase {

    override val locationStateFlow = locationRepository.locationStateFlow

    override val compassStateFlow = compassRepository.compassStateFlow
    private val _selectedPointState = MutableStateFlow<GeoPoint?>(null)
    override val selectedPointState = _selectedPointState.asStateFlow()

    override fun startNavigationMonitoring() {
        locationRepository.startLocationMonitoring()
        compassRepository.startCompass()
    }

    override fun stopNavigationMonitoring() {
        locationRepository.stopLocationMonitoring()
        compassRepository.stopCompass()
    }

    override suspend fun onSelectPoint(pointName: String?) {
        geoPointsRepository.points.value.firstOrNull { it.name == pointName }
    }

}