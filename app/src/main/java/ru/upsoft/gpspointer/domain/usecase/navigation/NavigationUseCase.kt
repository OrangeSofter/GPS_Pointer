package ru.upsoft.gpspointer.domain.usecase.navigation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.model.LocationState
import ru.upsoft.gpspointer.domain.model.SelectedPointState
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository
import ru.upsoft.gpspointer.domain.repository.LocationRepository
import javax.inject.Inject

interface NavigationUseCase {

    val locationStateFlow: StateFlow<LocationState>

    val compassStateFlow: StateFlow<CompassState>

    val selectedPointState: StateFlow<SelectedPointState?>

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
    private val _selectedPointState = MutableStateFlow<SelectedPointState?>(null)
    override val selectedPointState = _selectedPointState.asStateFlow()

    override fun startNavigationMonitoring() {
        locationRepository.startLocationMonitoring()
        compassRepository.startCompass()
    }

    override fun stopNavigationMonitoring() {
        locationRepository.stopLocationMonitoring()
        compassRepository.stopCompass()
    }

    override suspend fun onSelectPoint(pointName: String?) = withContext(Dispatchers.Default) {
        val points = geoPointsRepository.loadPoints()
        val selectedPoint = points.firstOrNull { it.name == pointName }
        if (selectedPoint == null) {
            _selectedPointState.value = null
            return@withContext
        }
        val currentLocation = (locationStateFlow
            .first { it is LocationState.LocationRetrieved } as LocationState.LocationRetrieved)
            .location

        val degreeToPoint = currentLocation.calculateDegrees(selectedPoint.location)
        val distanceToPoint = currentLocation.calculateKilometersDistance(selectedPoint.location)
        _selectedPointState.value = SelectedPointState(
            selectedPoint = selectedPoint,
            degreeToPoint = degreeToPoint,
            kilometersToPoint = distanceToPoint,
        )

    }

}