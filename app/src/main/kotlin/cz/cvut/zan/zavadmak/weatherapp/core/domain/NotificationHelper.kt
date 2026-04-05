package cz.cvut.zan.zavadmak.weatherapp.core.domain

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.MainActivity
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens
import cz.cvut.zan.zavadmak.weatherapp.core.utils.NOTIFICATION_CHANNEL_ID

class NotificationHelper(private val context: Context) {

    fun notify(type: Int) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        when (type) {
            Notifications.AVAILABLE -> notifyNotificationsActivation()
            Notifications.MORNING_FORECAST -> notifyMorningForecast(intent)
        }
    }

    @SuppressLint("MissingPermission")
    fun notifyMorningForecast(intent: Intent) {
        // Wrap it in a PendingIntent
        val pendingIntent = PendingIntent.getActivity(
            context,
            Notifications.MORNING_FORECAST,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Morning forecast")
            .setContentText("Check out today's weather!")
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(Notifications.MORNING_FORECAST, notification)
        }
    }

    @SuppressLint("MissingPermission")
    fun notifyNotificationsActivation() {
        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notifications activated")
            .setAutoCancel(true)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(Notifications.AVAILABLE, notification)
        }
    }
}