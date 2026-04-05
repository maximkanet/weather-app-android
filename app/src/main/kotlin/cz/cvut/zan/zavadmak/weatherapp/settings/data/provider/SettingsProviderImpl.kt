package cz.cvut.zan.zavadmak.weatherapp.settings.data.provider

import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnitType

class SettingsProviderImpl(
    private val repository: SettingsRepository
) : SettingsProvider {
    override suspend fun getTemperatureUnit(): WeatherUnit {
        return repository.getUnit(WeatherUnitType.Temperature)
    }

    override suspend fun getWindUnit(): WeatherUnit {
        return repository.getUnit(WeatherUnitType.WindSpeed)
    }

    override suspend fun getPrecipitationUnit(): WeatherUnit {
        return repository.getUnit(WeatherUnitType.Precipitation)
    }

    override suspend fun isNotificationsAllow(): Boolean {
        return repository.getNotificationState(NotificationType.ALL).checked
    }
}