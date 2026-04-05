package cz.cvut.zan.zavadmak.weatherapp.di

import androidx.room.Room
import cz.cvut.zan.zavadmak.weatherapp.core.data.local.WeatherAppDatabase
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.dao.LocationDao
import org.koin.dsl.module

val databaseModule = module {

    // -------- Database --------

    single<WeatherAppDatabase> {
        Room.databaseBuilder(
            get(),
            WeatherAppDatabase::class.java,
            "weather_app_database"
        ).build()
    }

    // --------- Dao ------------

    single<LocationDao> {
        get<WeatherAppDatabase>().locationDao()
    }

}