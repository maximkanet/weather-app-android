package cz.cvut.zan.zavadmak.weatherapp.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.dao.LocationDao
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity.LocationEntity

@Database(entities = [LocationEntity::class], version = 4)
abstract class WeatherAppDatabase : RoomDatabase() {

    abstract fun locationDao(): LocationDao

}