package ru.upsoft.gpspointer.presentation.screens.savepoint

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.upsoft.gpspointer.common.sendLog
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.Location
import ru.upsoft.gpspointer.domain.model.LocationState
import ru.upsoft.gpspointer.domain.usecase.navigation.NavigationUseCase
import javax.inject.Inject

@HiltViewModel
class SavePointViewModel @Inject constructor(
    private val navigationUseCase: NavigationUseCase
) : ViewModel() {

    val saveCurrentLocationAvailableState = navigationUseCase
        .locationStateFlow
        .map { it is LocationState.LocationRetrieved }
        .onEach {
            sendLog("saveCurrentLocationAvailableState $it")
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = false,
        )

    private val _popBackState = MutableStateFlow(false)
    val popBackState = _popBackState.asStateFlow()


    fun savePoint(pointName: String, coordinates: String) = viewModelScope.launch {
        val location = parseInputCoordinates(coordinates) ?: return@launch
        navigationUseCase.safePoint(GeoPoint(pointName, location))
        _popBackState.value = true
    }

    fun savePointByCurrentLocation(pointName: String) = viewModelScope.launch {
        val state = (navigationUseCase.locationStateFlow.value as? LocationState.LocationRetrieved)
            ?: return@launch
        navigationUseCase.safePoint(GeoPoint(pointName, state.location))
        _popBackState.value = true
    }

    fun inputCoordinatesIsCorrect(coordinates: String): Boolean =
        parseInputCoordinates(coordinates) != null

    private fun parseInputCoordinates(coordinates: String): Location? {
        val coordinatesList = coordinates.split(", ")
        val lat = coordinatesList
            .getOrNull(0)
            ?.replace(',', '.')
            ?.toDoubleOrNull() ?: return null
        val long = coordinatesList
            .getOrNull(1)
            ?.replace(',', '.')
            ?.toDoubleOrNull() ?: return null

        return Location(lat, long)
    }
}