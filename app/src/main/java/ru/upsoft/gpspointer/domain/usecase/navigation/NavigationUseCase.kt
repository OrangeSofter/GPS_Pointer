package ru.upsoft.gpspointer.domain.usecase.navigation

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.CompassState
import ru.upsoft.gpspointer.domain.model.LocationState

interface NavigationUseCase {

    val locationStateFlow: StateFlow<LocationState>

    val compassStateFlow: StateFlow<CompassState>

    fun startNavigationMonitoring()

    fun stopNavigationMonitoring()

}