package cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "location",
    indices = [Index(value = ["longitude", "latitude"], unique = true)]
)
data class LocationEntity(
    @PrimaryKey(autoGenerate = true)
    val placeId: Long = 0,
    val longitude: Double,
    val latitude: Double,
    val name: String,
    val state: String,
    val country: String,
    val lastUse: Long
)
