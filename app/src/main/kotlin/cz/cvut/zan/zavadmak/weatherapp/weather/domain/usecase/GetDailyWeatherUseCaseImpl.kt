package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import kotlinx.datetime.LocalDateTime

class GetDailyWeatherUseCaseImpl : GetDailyWeatherUseCase {
    override suspend fun execute(
        lon: Double,
        lat: Double
    ): DailyWeather {
        return DailyWeather(
            time = LocalDateTime.parse("2025-04-27T10:30"),
            sunrise = LocalDateTime.parse("2025-04-27T05:34"),
            sunset = LocalDateTime.parse("2025-04-27T20:34"),
            temperatureMin = 4.5,
            temperatureMax = 6.7,
            weatherCode = 7262
        )
    }
}