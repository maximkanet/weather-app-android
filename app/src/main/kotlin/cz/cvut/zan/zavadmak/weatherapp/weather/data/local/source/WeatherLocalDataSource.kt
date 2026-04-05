package cz.cvut.zan.zavadmak.weatherapp.weather.data.local.source

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

interface WeatherLocalDataSource {

    suspend fun getWeather(longitude: Double, latitude: Double, time: Long): Weather?
    suspend fun setWeather(longitude: Double, latitude: Double, weather: Weather)

}