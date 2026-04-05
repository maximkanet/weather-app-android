package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

interface GetForecastUseCase {

    suspend fun execute(lon: Double, lat: Double): List<Weather>

}