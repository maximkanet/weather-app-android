package cz.cvut.zan.zavadmak.weatherapp.core.domain

import android.content.Context
import androidx.annotation.RequiresPermission
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.utils.NOTIFICATION_CHANNEL_ID

class NotificationHelper(private val context: Context) {

    @RequiresPermission(android.Manifest.permission.POST_NOTIFICATIONS)
    fun notifyNotificationsAreAvailable() {

        val notification = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notifications are available")
            .setAutoCancel(true)
            .setOngoing(true)
            .build()

        with(NotificationManagerCompat.from(context)) {
            notify(2, notification)
        }
    }

//    fun notifyMorningForecast(code: String, minTemperature: String, maxTemperature: String) {
//        val intent = Intent(context, MainActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//        val pendingIntent: PendingIntent = PendingIntent.getActivity(
//            context, 0, intent,
//            PendingIntent.FLAG_MUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
//        )
//
//        val notification = NotificationCompat
//            .Builder(context, NOTIFICATION_CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("Today's weather")
//            .setContentText("$code. $minTemperature / $maxTemperature")
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//            .setOngoing(true)
//            .build()
//
//        val notificationManager =
//            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        notificationManager.notify(1, notification)
//    }
}