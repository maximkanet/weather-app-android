package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model

import androidx.annotation.StringRes
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit

data class UnitUiState(
    @StringRes
    val optionName: Int,
    @StringRes
    val optionDescription: Int?,
    val current: WeatherUnit,
    val all: List<WeatherUnit>
)
