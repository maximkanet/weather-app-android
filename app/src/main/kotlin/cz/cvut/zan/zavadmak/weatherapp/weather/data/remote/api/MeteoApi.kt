package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.api

import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.DailyDto
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.WeatherDto

interface MeteoApi {
    suspend fun getCurrentWeather(longitude: Double, latitude: Double): WeatherDto
    suspend fun getDailyWeather(longitude: Double, latitude: Double): List<DailyDto>
    suspend fun getHourlyWeather(longitude: Double, latitude: Double): List<WeatherDto>
}