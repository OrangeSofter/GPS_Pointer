package ru.upsoft.gpspointer.data.mapper

import ru.upsoft.gpspointer.data.database.geopoints.PointEntity
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.Location


fun List<PointEntity>.toDomainModel() = map { it.toDomainModel() }

fun PointEntity.toDomainModel(): GeoPoint {
    return GeoPoint(
        name = name,
        Location(
            latitude = latitude,
            longitude = longitude,
        )
    )
}
