package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepository
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather

class GetDailyWeatherUseCaseImpl(
    private val repository: WeatherRepository
) : GetDailyWeatherUseCase {
    override suspend fun execute(longitude: Double, latitude: Double): List<DailyWeather> {
        return repository.getDailyWeather(longitude = longitude, latitude = latitude)
    }
}