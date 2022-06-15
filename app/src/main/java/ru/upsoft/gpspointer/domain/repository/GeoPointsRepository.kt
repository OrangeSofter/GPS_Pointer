package ru.upsoft.gpspointer.domain.repository

import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.SimpleResult

interface GeoPointsRepository {

    suspend fun loadPoints(): List<GeoPoint>

    suspend fun safePoint(point: GeoPoint): SimpleResult

    suspend fun deletePoint(pointName: String)
}