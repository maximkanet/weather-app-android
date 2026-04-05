package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepository
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.utils.getCurrentDateTime
import kotlinx.datetime.DatePeriod

class GetHourlyWeatherUseCaseImpl(
    private val repository: WeatherRepository
) : GetHourlyWeatherUseCase {
    override suspend fun execute(
        latitude: Double,
        longitude: Double,
//        period: DatePeriod
    ): List<Weather> {
        return repository.getForecast(
            latitude = latitude,
            longitude = longitude,
            range = 2
        )
    }
}