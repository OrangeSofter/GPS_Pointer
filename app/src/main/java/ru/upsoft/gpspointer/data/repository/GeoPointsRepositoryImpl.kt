package ru.upsoft.gpspointer.data.repository

import android.content.Context
import androidx.room.Room
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import ru.upsoft.gpspointer.data.database.AppDatabase
import ru.upsoft.gpspointer.data.mapper.toDomainModel
import ru.upsoft.gpspointer.data.mapper.toEntity
import ru.upsoft.gpspointer.domain.model.GeoPoint
import ru.upsoft.gpspointer.domain.model.SimpleResult
import ru.upsoft.gpspointer.domain.repository.GeoPointsRepository
import javax.inject.Inject

class GeoPointsRepositoryImpl @Inject constructor(
    @ApplicationContext context: Context
) : GeoPointsRepository {

    private val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "app-db"
    ).build()

    private val dao = db.pointsDao()

    private val _pointsState = MutableStateFlow<List<GeoPoint>>(emptyList())
    override val pointsState = _pointsState.asStateFlow()


    override suspend fun safePoint(point: GeoPoint): SimpleResult = withContext(Dispatchers.IO) {
        val entity = point.toEntity()
        return@withContext try {
            dao.insert(entity)
            _pointsState.value = _pointsState.value.plus(point)
            SimpleResult.SUCCESS
        } catch (e: Exception) {
            SimpleResult.FAILED
        }

    }

    override suspend fun deletePoint(pointName: String) = withContext(Dispatchers.IO) {
        dao.deleteByName(pointName)

        _pointsState.value = _pointsState.value.filter { it.name != pointName }
    }

    override suspend fun loadPoints(): List<GeoPoint> = withContext(Dispatchers.IO) {
        val points = dao.getAll().toDomainModel()
        _pointsState.value = points
        return@withContext points
    }
}