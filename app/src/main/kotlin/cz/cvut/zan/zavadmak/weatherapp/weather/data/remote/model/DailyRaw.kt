package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyRaw(
    val time: List<String>,
    @SerialName("weather_code")
    val weatherCode: List<Int>,
    val sunrise: List<String>,
    val sunset: List<String>,
    @SerialName("temperature_2m_min")
    val temperatureMin: List<Double>,
    @SerialName("temperature_2m_max")
    val temperatureMax: List<Double>,
)
