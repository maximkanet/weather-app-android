package cz.cvut.zan.zavadmak.weatherapp.location.domain.model

import kotlinx.datetime.LocalDateTime

data class Location(
    val id: Long,
    val longitude: Double,
    val latitude: Double,
    val name: String,
    val state: String,
    val country: String,
    val lastUse: LocalDateTime
)