package cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location")
data class LocationEntity(
    @PrimaryKey
    val placeId: Long,
    val longitude: Double,
    val latitude: Double,
    val name: String,
    val state: String,
    val country: String,
)
