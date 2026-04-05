package cz.cvut.zan.zavadmak.weatherapp.settings.data.repository

import cz.cvut.zan.zavadmak.weatherapp.settings.data.local.source.SettingsLocalDataSource
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationState
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnitType
import kotlinx.coroutines.flow.first

class SettingsRepositoryImpl(
    private val dataSource: SettingsLocalDataSource
) : SettingsRepository {

    override suspend fun getUnit(type: WeatherUnitType): WeatherUnit {

        return when (type) {
            WeatherUnitType.Temperature -> WeatherUnit.findByType(
                type, dataSource.temperature.first() ?: "",
            ) ?: WeatherUnit.Celsius()

            WeatherUnitType.WindSpeed -> WeatherUnit.findByType(
                type, dataSource.windSpeed.first() ?: "",
                ) ?: WeatherUnit.MetersPerSecond()

            WeatherUnitType.Precipitation -> WeatherUnit.findByType(
                type, dataSource.precipitation.first() ?: ""
            ) ?: WeatherUnit.Millimeters()
        }
    }

    override suspend fun setUnit(unit: WeatherUnit) {
        when (unit.type) {
            WeatherUnitType.Temperature -> dataSource.saveTemperatureToken(unit.name)
            WeatherUnitType.WindSpeed -> dataSource.saveWindSpeedToken(unit.name)
            WeatherUnitType.Precipitation -> dataSource.savePrecipitationToken(unit.name)
        }
    }

    override suspend fun getNotificationState(type: NotificationType): NotificationState {
        return when (type) {
            NotificationType.ALL -> NotificationState(
                type = type,
                checked = dataSource.allowNotifications.first() ?: false
            )

            NotificationType.MORNING_FORECAST -> NotificationState(
                type = type,
                checked = dataSource.allowMorningForecastNotification.first() ?: false
            )
        }
    }

    override suspend fun setNotificationState(
        type: NotificationType,
        checked: Boolean
    ) {

        when (type) {
            NotificationType.ALL -> dataSource.saveAllowNotificationsToken(checked)
            NotificationType.MORNING_FORECAST -> dataSource.saveAllowMorningForecastNotificationToken(
                checked
            )
        }

    }
}