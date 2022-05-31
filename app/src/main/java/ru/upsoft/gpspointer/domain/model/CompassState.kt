package ru.upsoft.gpspointer.domain.model

sealed interface CompassState {
    data class Loaded(val degree: Double) : CompassState
}

