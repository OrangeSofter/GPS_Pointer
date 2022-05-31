package ru.upsoft.gpspointer.domain.model

sealed class LocationState {
    object Loading : LocationState()
    class LocationRetrieved(val location: Location) : LocationState()
    class Failed(val failure: LocationFailure) : LocationState()
}

enum class LocationFailure {
    PERMISSION_DENIED,
    GMS_UNAVAILABLE
}