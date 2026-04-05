package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits

class GetWeatherUnitsUseCaseImpl : GetWeatherUnitsUseCase {
    override suspend fun execute(): WeatherUnits {
        return WeatherUnits.asDefault()
    }
}