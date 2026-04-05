package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    val time: String,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val weatherCode: Int,
    val sunrise: String,
    val sunset: String
)
