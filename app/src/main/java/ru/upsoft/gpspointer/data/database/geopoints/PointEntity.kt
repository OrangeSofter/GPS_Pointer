package ru.upsoft.gpspointer.data.database.geopoints

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "geo_points", primaryKeys = ["name"])
data class PointEntity(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "latitude") val latitude: Double,
    @ColumnInfo(name = "longitude") val longitude: Double,
)