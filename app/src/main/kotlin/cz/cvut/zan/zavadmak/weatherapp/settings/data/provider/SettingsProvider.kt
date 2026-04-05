package cz.cvut.zan.zavadmak.weatherapp.settings.data.provider

import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit

interface SettingsProvider {

    suspend fun getTemperatureUnit(): WeatherUnit
    suspend fun getWindUnit(): WeatherUnit
    suspend fun getPrecipitationUnit(): WeatherUnit

    suspend fun isNotificationsAllow(): Boolean

}