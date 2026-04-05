package cz.cvut.zan.zavadmak.weatherapp.weather.data.repository

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit

interface WeatherRepository {

    suspend fun getWeatherUnits(): WeatherUnit

    suspend fun getCurrentWeather(longitude: Double, latitude: Double): Weather

    suspend fun getDailyWeather(longitude: Double, latitude: Double): List<DailyWeather>

    suspend fun getForecast(longitude: Double, latitude: Double, range: Int): List<Weather>

}