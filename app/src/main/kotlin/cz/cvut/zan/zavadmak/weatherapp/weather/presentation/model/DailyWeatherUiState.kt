package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model

import androidx.annotation.DrawableRes

data class DailyWeatherUiState(
    val day: String,
    val date: String,
    val codeString: String,
    @DrawableRes
    val icon: Int,
    val temperatureMin: String,
    val temperatureMax: String,
    val sunrise: String,
    val sunset: String,
)
