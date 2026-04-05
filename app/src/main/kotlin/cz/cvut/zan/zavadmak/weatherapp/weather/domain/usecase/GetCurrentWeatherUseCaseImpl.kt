package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import kotlinx.datetime.LocalDateTime

class GetCurrentWeatherUseCaseImpl : GetCurrentWeatherUseCase {
    override suspend fun execute(
        lon: Double,
        lat: Double
    ): Weather {
        return Weather(
            time = LocalDateTime.parse("2025-04-27T10:00"),
            weatherCode = 1,
            temperature = 12.0,
            wind = 3.0,
            windGusts = 10.0,
            windDirection = 45.0,
            humidity = 8.0,
            precipitation = 1.0,
            precipitationProbability = 40.0
        )
    }
}