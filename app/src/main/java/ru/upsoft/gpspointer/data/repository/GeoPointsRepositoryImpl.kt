package ru.upsoft.gpspointer.data.repository

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.upsoft.gpspointer.data.database.AppDatabase
import ru.upsoft.gpspointer.data.mapper.toDomainModel
import ru.upsoft.gpspointer.data.mapper.toEntity
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository
import javax.inject.Inject

class GeoPointsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : GeoPointsRepository {//Todo: стоит ли выносить в другой дисретчер корутин

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "app-db"
    ).build()

    private val dao = db.pointsDao()

    override suspend fun safePoint(point: GeoPoint) = withContext(Dispatchers.IO)  {
        val entity = point.toEntity()
        dao.insert(entity)
    }

    override suspend fun deletePoint(pointName: String) = withContext(Dispatchers.IO)  {
        dao.deleteByName(pointName)
    }

    override suspend fun loadPoints(): List<GeoPoint> = withContext(Dispatchers.IO) {
        return@withContext dao.getAll().toDomainModel()
    }
}