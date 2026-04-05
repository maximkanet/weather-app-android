package cz.cvut.zan.zavadmak.weatherapp.settings.data.repository

import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationState
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnitType

interface SettingsRepository {
    suspend fun getUnit(type: WeatherUnitType): WeatherUnit
    suspend fun setUnit(unit: WeatherUnit)

    suspend fun getNotificationState(type: NotificationType): NotificationState
    suspend fun setNotificationState(type: NotificationType, checked: Boolean)
}