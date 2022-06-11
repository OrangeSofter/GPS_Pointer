package ru.upsoft.gpspointer.domain.model

sealed interface CompassState {
    object Initial : CompassState
    data class Loaded(val degree: Float) : CompassState
    data class Failed(val Failure: CompassFailure) : CompassState
}

enum class CompassFailure {
    HAVE_NOT_SENSOR,
}

