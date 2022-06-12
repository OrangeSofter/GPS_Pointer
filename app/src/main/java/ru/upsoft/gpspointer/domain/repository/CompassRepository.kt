package ru.upsoft.gpspointer.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.CompassState

interface CompassRepository {
    val compassStateFlow: StateFlow<CompassState>

    fun startCompass()

    fun stopCompass()
}