package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather

interface GetDailyWeatherUseCase {

    suspend fun execute(lon: Double, lat: Double): DailyWeather

}