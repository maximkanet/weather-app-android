package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepository
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits

class GetWeatherUnitsUseCaseImpl(
    private val repository: WeatherRepository
) : GetWeatherUnitsUseCase {
    override suspend fun execute(): WeatherUnits {
        return repository.getWeatherUnits()
    }
}