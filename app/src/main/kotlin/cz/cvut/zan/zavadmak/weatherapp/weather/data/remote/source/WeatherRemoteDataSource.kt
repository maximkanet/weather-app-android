package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.source

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

interface WeatherRemoteDataSource {
    suspend fun getCurrentWeather(longitude: Double, latitude: Double): Weather
    suspend fun getDailyWeather(longitude: Double, latitude: Double): List<DailyWeather>
    suspend fun getForecast(longitude: Double, latitude: Double): List<Weather>
}