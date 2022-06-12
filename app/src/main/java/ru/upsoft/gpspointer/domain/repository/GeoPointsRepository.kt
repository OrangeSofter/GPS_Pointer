package ru.upsoft.gpspointer.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.GeoPoint

interface GeoPointsRepository {

    val points: StateFlow<List<GeoPoint>>

    suspend fun safePoint(point: GeoPoint)

    suspend fun deletePoint(pointName: String)
}