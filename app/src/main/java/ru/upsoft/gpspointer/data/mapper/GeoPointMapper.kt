package ru.upsoft.gpspointer.data.mapper

import ru.upsoft.gpspointer.data.database.geopoints.PointEntity
import ru.upsoft.gpspointer.domain.model.GeoPoint

fun GeoPoint.toEntity(): PointEntity {
    return PointEntity(
        name = name,
        latitude = location.latitude,
        longitude = location.longitude,
    )
}