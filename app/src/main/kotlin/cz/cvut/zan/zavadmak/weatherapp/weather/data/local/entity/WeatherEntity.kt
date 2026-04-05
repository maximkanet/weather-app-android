package cz.cvut.zan.zavadmak.weatherapp.weather.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity.LocationEntity

@Entity(
    tableName = "weather",
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
data class WeatherEntity(
    val placeId: Long,
    val time: Long, // time in seconds (UTC+0)
    val temperature: Double,
    val weatherCode: Int,
    val wind: Double,
    val windGusts: Double,
    val windDirection: Double,
    val humidity: Double,
    val precipitation: Double
)
