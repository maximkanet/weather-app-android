package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.source

import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.api.MeteoApi
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.mapper.toDomainModel

class WeatherRemoteDataSourceImpl(
    private val api: MeteoApi
) : WeatherRemoteDataSource {
    override suspend fun getCurrentWeather(
        longitude: Double,
        latitude: Double
    ): Weather {
        return api.getCurrentWeather(longitude = longitude, latitude = latitude).toDomainModel()
    }

    override suspend fun getDailyWeather(
        longitude: Double,
        latitude: Double
    ): List<DailyWeather> {
        return api.getDailyWeather(longitude = longitude, latitude = latitude)
            .map { it.toDomainModel() }
    }

    override suspend fun getForecast(
        longitude: Double,
        latitude: Double
    ): List<Weather> {
        return api.getHourlyWeather(longitude = longitude, latitude = latitude)
            .map { it.toDomainModel() }
    }
}