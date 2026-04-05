package cz.cvut.zan.zavadmak.weatherapp.core

import android.app.Application
import cz.cvut.zan.zavadmak.weatherapp.di.initKoin
import org.koin.android.ext.koin.androidContext

class WeatherApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@WeatherApp)
        }
    }
}