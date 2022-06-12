package ru.upsoft.gpspointer.data.repository

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onSubscription
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository

class GeoPointsRepositoryImpl : GeoPointsRepository {

    private val _points = MutableStateFlow(emptyList<GeoPoint>()).apply {
        onSubscription { loadPoints() }
    }
    override val points = _points.asStateFlow()

    override suspend fun safePoint(point: GeoPoint) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePoint(pointName: String) {
        TODO("Not yet implemented")
    }

    private suspend fun loadPoints(): List<GeoPoint> {
        TODO()
    }
}