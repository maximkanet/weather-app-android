package cz.cvut.zan.zavadmak.weatherapp.location.domain.model

data class Location(
    val id: Long,
    val longitude: Double,
    val latitude: Double,
    val name: String,
    val state: String,
    val country: String
)