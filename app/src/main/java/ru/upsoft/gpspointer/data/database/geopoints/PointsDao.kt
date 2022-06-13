package ru.upsoft.gpspointer.data.database.geopoints

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PointsDao {
    @Query("SELECT * FROM geo_points")
    fun getAll(): List<PointEntity>

    @Insert
    fun insert(entity: PointEntity)

    @Delete
    fun delete(entity: PointEntity)

    @Query("DELETE FROM geo_points WHERE geo_points.name = :name")
    fun deleteByName(name: String)
}