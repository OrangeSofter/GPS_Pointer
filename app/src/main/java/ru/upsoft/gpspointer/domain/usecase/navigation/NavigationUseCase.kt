package ru.upsoft.gpspointer.domain.usecase.navigation

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.core.model.LocationState

interface NavigationUseCase {

    val locationStateFlow: StateFlow<LocationState>

    fun startLocationMonitoring()

    fun stopLocationMonitoring()

}