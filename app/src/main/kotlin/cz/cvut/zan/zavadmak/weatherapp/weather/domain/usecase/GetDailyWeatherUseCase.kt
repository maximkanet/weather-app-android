package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather

interface GetDailyWeatherUseCase {

    suspend fun execute(longitude: Double, latitude: Double): List<DailyWeather>

}