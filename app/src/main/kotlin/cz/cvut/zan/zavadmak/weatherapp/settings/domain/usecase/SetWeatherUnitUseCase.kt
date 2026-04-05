package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit

interface SetWeatherUnitUseCase {

    suspend fun execute(unit: WeatherUnit)

}