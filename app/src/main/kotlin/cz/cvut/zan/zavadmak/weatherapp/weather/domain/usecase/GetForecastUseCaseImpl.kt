package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import kotlinx.datetime.LocalDateTime

class GetForecastUseCaseImpl : GetForecastUseCase {
    override suspend fun execute(
        lon: Double,
        lat: Double
    ): List<Weather> {
        return listOf(
            Weather(
                time = LocalDateTime.parse("2025-04-24T00:00"),
                weatherCode = 1,
                temperature = 14.15,
                wind = 16.17,
                windGusts = 18.19,
                windDirection = 20.21,
                humidity = 22.23,
                precipitation = 24.25,
                precipitationProbability = 26.27
            ),
            Weather(
                time = LocalDateTime.parse("2025-04-24T01:00"),
                weatherCode = 1,
                temperature = 14.15,
                wind = 16.17,
                windGusts = 18.19,
                windDirection = 20.21,
                humidity = 22.23,
                precipitation = 24.25,
                precipitationProbability = 26.27
            ),
            Weather(
                time = LocalDateTime.parse("2025-04-24T02:00"),
                weatherCode = 1,
                temperature = 14.15,
                wind = 16.17,
                windGusts = 18.19,
                windDirection = 20.21,
                humidity = 22.23,
                precipitation = 24.25,
                precipitationProbability = 26.27
            ),
            Weather(
                time = LocalDateTime.parse("2025-04-24T03:00"),
                weatherCode = 1,
                temperature = 14.15,
                wind = 16.17,
                windGusts = 18.19,
                windDirection = 20.21,
                humidity = 22.23,
                precipitation = 24.25,
                precipitationProbability = 26.27
            ),
        )
    }
}