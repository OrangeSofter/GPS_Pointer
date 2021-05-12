package ru.upsoft.gpspointer.core.model

import android.location.Location

sealed class LocationState {
    object Loading : LocationState()
    class LocationRetrieved(val location: Location) : LocationState()
    class Failed(val failure: LocationFailure) : LocationState()
}

enum class LocationFailure {
    PERMISSION_DENIED,
    GMS_UNAVAILABLE
}