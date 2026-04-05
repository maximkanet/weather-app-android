package cz.cvut.zan.zavadmak.weatherapp.settings.data.local.source

import kotlinx.coroutines.flow.Flow

interface SettingsLocalDataSource {
    val temperature: Flow<String?>
    val windSpeed: Flow<String?>
    val precipitation: Flow<String?>

    suspend fun saveTemperatureToken(token: String)
    suspend fun saveWindSpeedToken(token: String)
    suspend fun savePrecipitationToken(token: String)

    val allowNotifications: Flow<Boolean?>
    val allowMorningForecastNotification: Flow<Boolean?>
    suspend fun saveAllowNotificationsToken(token: Boolean)
    suspend fun saveAllowMorningForecastNotificationToken(token: Boolean)
}