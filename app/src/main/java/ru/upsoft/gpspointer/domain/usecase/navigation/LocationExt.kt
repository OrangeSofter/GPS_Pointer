package ru.upsoft.gpspointer.domain.usecase.navigation

import ru.upsoft.gpspointer.domain.model.Location

fun Location.calculateDegrees(other: Location): Float {
    val curLocation = android.location.Location("temporary location").apply {
        latitude = this@calculateDegrees.latitude
        longitude = this@calculateDegrees.longitude
    }
    val otherLocation = android.location.Location("temporary location").apply {
        latitude = other.latitude
        longitude = other.longitude
    }
    return curLocation.bearingTo(otherLocation)
}

fun Location.calculateKilometersDistance(other: Location): Float {
    val curLocation = android.location.Location("temporary location").apply {
        latitude = this@calculateKilometersDistance.latitude
        longitude = this@calculateKilometersDistance.longitude
    }
    val otherLocation = android.location.Location("temporary location").apply {
        latitude = other.latitude
        longitude = other.longitude
    }
    return curLocation.distanceTo(otherLocation) / 1000
}
