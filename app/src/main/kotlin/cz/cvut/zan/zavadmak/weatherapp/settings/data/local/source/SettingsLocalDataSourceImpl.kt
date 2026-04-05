package cz.cvut.zan.zavadmak.weatherapp.settings.data.local.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsLocalDataSourceImpl(
    val context: Context
) : SettingsLocalDataSource {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private object Keys {
        // units
        val TEMPERATURE_UNIT = stringPreferencesKey("temperature_unit")
        val WIND_SPEED_UNIT = stringPreferencesKey("wind_speed_unit")
        val PRECIPITATION_UNIT = stringPreferencesKey("precipitation_unit")

        // notifications
        val ALLOW_NOTIFICATIONS = booleanPreferencesKey("allow_notifications")
        val ALLOW_MORNING_FORECAST_NOTIFICATION =
            booleanPreferencesKey("allow_morning_weather_notification")
    }

    override val temperature: Flow<String?> = context.dataStore.data
        .map { it[Keys.TEMPERATURE_UNIT] }
    override val windSpeed: Flow<String?> = context.dataStore.data
        .map { it[Keys.WIND_SPEED_UNIT] }
    override val precipitation: Flow<String?> = context.dataStore.data
        .map { it[Keys.PRECIPITATION_UNIT] }

    override suspend fun saveTemperatureToken(token: String) {
        context.dataStore.edit { prefs -> prefs[Keys.TEMPERATURE_UNIT] = token }
    }

    override suspend fun saveWindSpeedToken(token: String) {
        context.dataStore.edit { prefs -> prefs[Keys.WIND_SPEED_UNIT] = token }
    }

    override suspend fun savePrecipitationToken(token: String) {
        context.dataStore.edit { prefs -> prefs[Keys.PRECIPITATION_UNIT] = token }
    }

    override val allowNotifications: Flow<Boolean?> = context.dataStore.data
        .map { it[Keys.ALLOW_NOTIFICATIONS] }
    override val allowMorningForecastNotification: Flow<Boolean?> = context.dataStore.data
        .map { it[Keys.ALLOW_MORNING_FORECAST_NOTIFICATION] }

    override suspend fun saveAllowNotificationsToken(token: Boolean) {
        context.dataStore.edit { prefs -> prefs[Keys.ALLOW_NOTIFICATIONS] = token }
    }

    override suspend fun saveAllowMorningForecastNotificationToken(token: Boolean) {
        context.dataStore.edit { prefs -> prefs[Keys.ALLOW_MORNING_FORECAST_NOTIFICATION] = token }
    }
}