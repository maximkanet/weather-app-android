package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepository
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import kotlinx.datetime.LocalDateTime

class GetForecastUseCaseImpl(
    private val repository: WeatherRepository
) : GetForecastUseCase {
    override suspend fun execute(longitude: Double, latitude: Double): List<Weather> {
        return repository.getForecast(longitude = longitude, latitude = latitude)
    }
}