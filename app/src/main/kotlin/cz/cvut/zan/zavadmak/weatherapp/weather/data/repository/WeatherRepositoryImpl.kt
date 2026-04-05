package cz.cvut.zan.zavadmak.weatherapp.weather.data.repository

import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.source.WeatherRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
//    private val localDataSource: WeatherLocalDataSource,
) : WeatherRepository {
    override suspend fun getWeatherUnits(): WeatherUnit {
        return WeatherUnit.defaults().first()
    }

    override suspend fun getCurrentWeather(longitude: Double, latitude: Double): Weather {
        return remoteDataSource.getCurrentWeather(longitude = longitude, latitude = latitude)
    }

    override suspend fun getDailyWeather(longitude: Double, latitude: Double): List<DailyWeather> {
        return remoteDataSource.getDailyWeather(longitude = longitude, latitude = latitude)
    }

    override suspend fun getForecast(
        longitude: Double,
        latitude: Double,
        range: Int
    ): List<Weather> {
        return remoteDataSource.getForecast(
            longitude = longitude,
            latitude = latitude,
            range = range
        )
    }
}