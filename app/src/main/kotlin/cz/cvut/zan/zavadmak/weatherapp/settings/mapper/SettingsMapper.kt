package cz.cvut.zan.zavadmak.weatherapp.settings.mapper

import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationState
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model.UnitUiState
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnitType
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model.NotificationUiState

fun WeatherUnit.toUiState(): UnitUiState {
    return when (this.type) {
        WeatherUnitType.Temperature -> UnitUiState(
            optionName = R.string.temperature_setting,
            optionDescription = null,
            current = this,
            all = WeatherUnit.allByType(this.type),
        )

        WeatherUnitType.WindSpeed -> UnitUiState(
            optionName = R.string.wind_speed_setting,
            optionDescription = null,
            current = this,
            all = WeatherUnit.allByType(this.type),
        )

        WeatherUnitType.Precipitation -> UnitUiState(
            optionName = R.string.precipitation_setting,
            optionDescription = null,
            current = this,
            all = WeatherUnit.allByType(this.type),
        )
    }
}

fun NotificationState.toUiState(): NotificationUiState {
    return when (type) {
        NotificationType.ALL -> NotificationUiState(
            optionName = R.string.allow_all_notifications,
            optionDescription = null,
            type = type,
            checked = checked,
        )

        NotificationType.MORNING_FORECAST -> NotificationUiState(
            optionName = R.string.enable_morning_forecast,
            optionDescription = R.string.enable_morning_forecast_description,
            type = type,
            checked = checked,
        )
    }
}