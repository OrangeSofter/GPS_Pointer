package ru.upsoft.gpspointer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.upsoft.gpspointer.data.database.geopoints.PointEntity
import ru.upsoft.gpspointer.data.database.geopoints.PointsDao

@Database(entities = [PointEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun pointsDao(): PointsDao
}