package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model

import androidx.annotation.DrawableRes

data class DailyWeatherUiState(
    val weatherCode: String,
    @DrawableRes
    val weatherIcon: Int,
    val temperatureMin: String,
    val temperatureMax: String,
    val sunrise: String,
    val sunset: String,
    val sunProgress: Float,
)
