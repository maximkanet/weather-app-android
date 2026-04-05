package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyRaw(
    val time: List<String>,
    @SerialName("weather_code")
    val weatherCode: List<Int>,
    @SerialName("temperature_2m")
    val temperature: List<Double>,
    @SerialName("wind_speed_10m")
    val wind: List<Double>,
    @SerialName("wind_gusts_10m")
    val windGusts: List<Double>,
    @SerialName("wind_direction_10m")
    val windDirection: List<Double>,
    @SerialName("relative_humidity_2m")
    val humidity: List<Double>,
    val precipitation: List<Double>
)
