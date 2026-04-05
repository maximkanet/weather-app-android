package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

interface GetForecastUseCase {

    suspend fun execute(longitude: Double, latitude: Double, range: Int): List<Weather>

}