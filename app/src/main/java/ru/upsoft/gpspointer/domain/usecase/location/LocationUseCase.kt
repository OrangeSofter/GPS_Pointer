package ru.upsoft.gpspointer.domain.usecase.location

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.core.model.LocationState

interface LocationUseCase {

    val locationStateFlow: StateFlow<LocationState>

    fun startLocationMonitoring()

    fun stopLocationMonitoring()

}