package ru.upsoft.gpspointer.domain.model

data class SelectedPointState(
    val selectedPoint: GeoPoint,
    val degreeToPoint: Float,
    val kilometersToPoint: Float,
)