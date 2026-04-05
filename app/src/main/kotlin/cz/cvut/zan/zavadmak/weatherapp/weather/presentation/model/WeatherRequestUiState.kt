package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model

import androidx.annotation.StringRes
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherRequest

data class WeatherRequestUiState(
    val request: WeatherRequest,
    @StringRes
    val stringRes: Int,
)
