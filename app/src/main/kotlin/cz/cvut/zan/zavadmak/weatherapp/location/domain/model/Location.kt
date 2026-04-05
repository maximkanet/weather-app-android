package cz.cvut.zan.zavadmak.weatherapp.location.domain.model

data class Location(
    val id: Int,
    val longitude: Double,
    val latitude: Double,
    val shortName: String,
    val fullName: String
)