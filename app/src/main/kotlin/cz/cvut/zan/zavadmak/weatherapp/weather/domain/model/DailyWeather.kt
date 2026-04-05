package cz.cvut.zan.zavadmak.weatherapp.weather.domain.model

import kotlinx.datetime.LocalDateTime

data class DailyWeather(
    val time: LocalDateTime,
    val sunrise: LocalDateTime,
    val sunset: LocalDateTime,
    val temperatureMin: Double,
    val temperatureMax: Double,
    val weatherCode: Int
)
