package cz.cvut.zan.zavadmak.weatherapp.weather.data.repository

import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.source.WeatherRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits

class WeatherRepositoryImpl(
    private val remoteDataSource: WeatherRemoteDataSource,
//    private val localDataSource: WeatherLocalDataSource,
) : WeatherRepository {
    override suspend fun getWeatherUnits(): WeatherUnits {
        return WeatherUnits.asDefault()
    }

    override suspend fun getCurrentWeather(longitude: Double, latitude: Double): Weather {
        return remoteDataSource.getCurrentWeather(longitude = longitude, latitude = latitude)
    }

    override suspend fun getDailyWeather(longitude: Double, latitude: Double): List<DailyWeather> {
        return remoteDataSource.getDailyWeather(longitude = longitude, latitude = latitude)
    }

    override suspend fun getForecast(longitude: Double, latitude: Double): List<Weather> {
        return remoteDataSource.getForecast(longitude = longitude, latitude = latitude)
    }
}