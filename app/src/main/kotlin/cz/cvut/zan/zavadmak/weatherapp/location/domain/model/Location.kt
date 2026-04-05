package cz.cvut.zan.zavadmak.weatherapp.location.domain.model

data class Location(
    val id: Int,
    val longitude: Double,
    val latitude: Double,
    val name: String,
    @Deprecated("")
    val fullName: String,
    val state: String,
    val country: String
)