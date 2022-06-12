package ru.upsoft.gpspointer.domain.model

sealed class LocationState {
    object Loading : LocationState()
    data class LocationRetrieved(val location: Location) : LocationState()
    data class Failed(val failure: LocationFailure) : LocationState()
}

enum class LocationFailure {
    PERMISSION_DENIED,
    GMS_UNAVAILABLE
}