package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherDto(
    val time: String,
    @SerialName("weather_code")
    val weatherCode: Int,
    @SerialName("temperature_2m")
    val temperature: Double,
    @SerialName("wind_speed_10m")
    val wind: Double,
    @SerialName("wind_gusts_10m")
    val windGusts: Double,
    @SerialName("wind_direction_10m")
    val windDirection: Double,
    @SerialName("relative_humidity_2m")
    val humidity: Double,
    val precipitation: Double,
)
