package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import kotlinx.datetime.LocalDateTime

class GetForecastUseCaseImpl : GetForecastUseCase {
    override suspend fun execute(
        lon: Double,
        lat: Double
    ): List<Weather> {
        return listOf()
    }
}