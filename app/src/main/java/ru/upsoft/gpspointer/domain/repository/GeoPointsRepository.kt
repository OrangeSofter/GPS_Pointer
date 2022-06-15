package ru.upsoft.gpspointer.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.SimpleResult

interface GeoPointsRepository {

    val pointsState: StateFlow<List<GeoPoint>>

    suspend fun loadPoints(): List<GeoPoint>

    suspend fun safePoint(point: GeoPoint): SimpleResult

    suspend fun deletePoint(pointName: String)
}