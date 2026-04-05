package cz.cvut.zan.zavadmak.weatherapp.weather.data.local.dao

import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import cz.cvut.zan.zavadmak.weatherapp.weather.data.local.entity.WeatherEntity

interface WeatherDao {

    @Query("SELECT * FROM weather WHERE time = :time AND placeId = :placeId LIMIT 1")
    suspend fun getWeather(placeId: Long, time: Long): WeatherEntity?

    @Insert(onConflict = REPLACE)
    suspend fun insertWeather(weatherEntity: WeatherEntity)

}