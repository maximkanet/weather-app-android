package cz.cvut.zan.zavadmak.weatherapp.core

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import cz.cvut.zan.zavadmak.weatherapp.core.utils.NOTIFICATION_CHANNEL_ID
import cz.cvut.zan.zavadmak.weatherapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()

        val mainChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Weather app main channel",
            NotificationManager.IMPORTANCE_HIGH
        )

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        manager.createNotificationChannel(mainChannel)

        initKoin {
            androidContext(this@WeatherApp)
        }
    }
}