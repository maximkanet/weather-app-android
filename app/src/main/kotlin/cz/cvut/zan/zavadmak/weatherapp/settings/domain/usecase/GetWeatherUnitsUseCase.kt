package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit

interface GetWeatherUnitsUseCase {

    suspend fun execute(): List<WeatherUnit>

}