package ru.upsoft.gpspointer.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.LocationState

interface LocationRepository {
    val locationStateFlow: StateFlow<LocationState>

    fun startLocationMonitoring()

    fun stopLocationMonitoring()

}