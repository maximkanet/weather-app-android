package cz.cvut.zan.zavadmak.weatherapp.weather.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity.LocationEntity

@Entity(
    tableName = "daily",
    primaryKeys = ["placeId", "time"],
    foreignKeys = [
        ForeignKey(
            entity = LocationEntity::class,
            parentColumns = ["placeId"],
            childColumns = ["placeId"],
            onUpdate = CASCADE,
            onDelete = CASCADE,
            deferred = false
        )
    ]
)
data class DailyEntity(
    val placeId: Int,
    val time: Long, // Time in seconds (UTC+0)
    val sunrise: Long, // in seconds (UTC+0)
    val sunset: Long, // in seconds (UTC+0)
    val temperatureMin: Double,
    val temperatureMax: Double,
    val weatherCode: Int
)
