package ru.upsoft.gpspointer.data.repository

import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.Location
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository
import javax.inject.Inject

class GeoPointsRepositoryImpl @Inject constructor(

) : GeoPointsRepository {

    override suspend fun safePoint(point: GeoPoint) {
        TODO("Not yet implemented")
    }

    override suspend fun deletePoint(pointName: String) {
        TODO("Not yet implemented")
    }

    override suspend fun loadPoints(): List<GeoPoint> {
        return listOf(
            GeoPoint("точка 1", Location(48.0, 48.0)),
            GeoPoint("точка 2", Location(49.0, 49.0)),
            GeoPoint("точка 3", Location(50.0, 51.0)),
        )
    }
}