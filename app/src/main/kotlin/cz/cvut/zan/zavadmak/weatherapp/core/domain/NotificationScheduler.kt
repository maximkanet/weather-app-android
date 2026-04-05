package cz.cvut.zan.zavadmak.weatherapp.core.domain

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType
import kotlinx.datetime.Clock

class NotificationScheduler(private val context: Context) {

    fun scheduleNotification(type: NotificationType) {
        val now = Clock.System.now().toEpochMilliseconds()

        when (type) {
            NotificationType.ALL -> {} // stub
            NotificationType.MORNING_FORECAST -> scheduleMorningForecast(now)
        }
    }

    fun cancelNotification(type: NotificationType) {
        when (type) {
            NotificationType.ALL -> {} // stub
            NotificationType.MORNING_FORECAST -> cancelMorningForecast()
        }
    }

    private fun scheduleMorningForecast(timeMillis: Long) {
        val intent = Intent(context, NotificationPublisher::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putExtra("route", MainScreens.Home.toString())
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            Notifications.MORNING_FORECAST,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        // Repeat every 24 hours (in milliseconds)
        val intervalMillis = 24 * 60 * 60 * 1000L

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            timeMillis,
            intervalMillis,
            pendingIntent
        )

        Log.i(
            "NotificationScheduler",
            "Morning forecast scheduled repeating. Start from $timeMillis ms"
        )
    }

    private fun cancelMorningForecast() {
        val intent = Intent(context, NotificationPublisher::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            Notifications.MORNING_FORECAST,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.cancel(pendingIntent)

        Log.i(
            "NotificationScheduler",
            "Morning forecast canceled"
        )
    }
}