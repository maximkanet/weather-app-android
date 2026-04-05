package cz.cvut.zan.zavadmak.weatherapp.weather.domain.model

import kotlinx.datetime.LocalDateTime

data class Weather (
    val time: LocalDateTime,
    val weatherCode: Int,
    val temperature: Double,
    val wind: Double,
    val windGusts: Double,
    val windDirection: Double,
    val humidity: Double,
    val precipitation: Double,
    val precipitationProbability: Double,
)
