package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.weather.data.repository.WeatherRepository
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather

class GetForecastUseCaseImpl(
    private val repository: WeatherRepository
) : GetForecastUseCase {
    override suspend fun execute(longitude: Double, latitude: Double, range: Int): List<Weather> {
        return repository
            .getForecast(longitude = longitude, latitude = latitude, range = range)
            .filter { it.time.time.hour % 2 == 0 }
    }
}