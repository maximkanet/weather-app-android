package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model

import androidx.annotation.DrawableRes

data class WeatherUiState(
    val weatherCode: String,
    @DrawableRes
    val weatherIcon: Int,
    val temperature: String,
    val wind: String,
    val windGusts: String,
    val windDirection: Double,
    val humidity: String,
    val precipitation: String,
    val precipitationProbability: String,
)
