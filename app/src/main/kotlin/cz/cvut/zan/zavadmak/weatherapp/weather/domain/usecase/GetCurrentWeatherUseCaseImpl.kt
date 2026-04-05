package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepository
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

class GetCurrentWeatherUseCaseImpl(
    private val repository: WeatherRepository
) : GetCurrentWeatherUseCase {
    override suspend fun execute(lon: Double, lat: Double): Weather {
        return repository.getCurrentWeather(longitude = lon, latitude = lat)
    }
}