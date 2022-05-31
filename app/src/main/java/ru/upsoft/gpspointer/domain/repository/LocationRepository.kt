package ru.upsoft.gpspointer.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.core.model.LocationState

interface LocationRepository {
    val locationStateFlow: StateFlow<LocationState>

    fun startLocationMonitoring()

    fun stopLocationMonitoring()

}