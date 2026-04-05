package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

interface GetHourlyWeatherUseCase {

    suspend fun execute(latitude: Double, longitude: Double,/* period: DatePeriod*/): List<Weather>

}