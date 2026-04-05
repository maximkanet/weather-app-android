package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class DailyWrapper(
    val daily: DailyRaw
)
