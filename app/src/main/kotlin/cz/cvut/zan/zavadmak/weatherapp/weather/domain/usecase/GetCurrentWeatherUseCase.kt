package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

interface GetCurrentWeatherUseCase {

    suspend fun execute(lon: Double, lat: Double): Weather

}