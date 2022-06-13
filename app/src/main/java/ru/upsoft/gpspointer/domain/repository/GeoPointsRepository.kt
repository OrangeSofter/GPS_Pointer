package ru.upsoft.gpspointer.domain.repository

import ru.upsoft.gpspointer.domain.model.GeoPoint

interface GeoPointsRepository {

    suspend fun loadPoints(): List<GeoPoint>

    suspend fun safePoint(point: GeoPoint)

    suspend fun deletePoint(pointName: String)
}