package ru.upsoft.gpspointer.domain.usecase.navigation

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext
import ru.upsoft.gpspointer.domain.model.*
import ru.upsoft.gpspointer.domain.repository.CompassRepository
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository
import ru.upsoft.gpspointer.domain.repository.LocationRepository
import javax.inject.Inject

interface NavigationUseCase {

    val locationStateFlow: StateFlow<LocationState>

    val compassStateFlow: StateFlow<CompassState>

    val selectedPointState: Flow<SelectedPointState?>

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

    private val selectedGeoPoint = MutableStateFlow<GeoPoint?>(null)
    override val selectedPointState = combine(
        selectedGeoPoint,
        locationStateFlow,
        compassStateFlow
    ) { selectedPoint, locationState, compassState ->
        calculateSelectedPointState(
            selectedPoint,
            (locationState as? LocationState.LocationRetrieved)?.location,
            (compassState as? CompassState.Loaded)?.degree,
        )
    }

    override fun startNavigationMonitoring() {
        locationRepository.startLocationMonitoring()
        compassRepository.startCompass()
    }

    override fun stopNavigationMonitoring() {
        locationRepository.stopLocationMonitoring()
        compassRepository.stopCompass()
    }

    override suspend fun onSelectPoint(pointName: String?) {
        val points = geoPointsRepository.loadPoints()
        val selectedPoint = points.firstOrNull { it.name == pointName }
        selectedGeoPoint.value = selectedPoint
    }

    private suspend fun calculateSelectedPointState(
        selectedPoint: GeoPoint?,
        currentLocation: Location?,
        compassDegree: Float?
    ): SelectedPointState? = withContext(Dispatchers.Default) {
        if (selectedPoint == null || currentLocation == null || compassDegree == null) {
            return@withContext null
        }
        val degreeToPoint = currentLocation.calculateDegrees(selectedPoint.location) - compassDegree
        val distanceToPoint = currentLocation.calculateKilometersDistance(selectedPoint.location)
        return@withContext SelectedPointState(
            selectedPoint = selectedPoint,
            degreeToPoint = degreeToPoint,
            kilometersToPoint = distanceToPoint,
        )
    }

}