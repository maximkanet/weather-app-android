package cz.cvut.zan.zavadmak.weatherapp.weather.data.local.source

import cz.cvut.zan.zavadmak.weatherapp.location.data.local.dao.LocationDao
import cz.cvut.zan.zavadmak.weatherapp.weather.data.local.dao.WeatherDao
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.mapper.toDataModel
import cz.cvut.zan.zavadmak.weatherapp.weather.mapper.toDomainModel

class WeatherLocalDataSourceImpl(
    private val weatherDao: WeatherDao,
    private val locationDao: LocationDao,
) : WeatherLocalDataSource {
    override suspend fun getWeather(
        longitude: Double,
        latitude: Double,
        time: Long,
    ): Weather? {
        val location = locationDao.getLocationByCoord(longitude = longitude, latitude = latitude)
        return if (location != null)
            weatherDao.getWeather(location.placeId, time)?.toDomainModel()
        else null
    }

    override suspend fun setWeather(
        longitude: Double,
        latitude: Double,
        weather: Weather
    ) {
        val location = locationDao.getLocationByCoord(longitude = longitude, latitude = latitude)
        if (location == null) return
        weatherDao.insertWeather(weather.toDataModel(location))
    }
}