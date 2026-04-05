package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits

interface GetWeatherUnitsUseCase {

    suspend fun execute(): WeatherUnits

}