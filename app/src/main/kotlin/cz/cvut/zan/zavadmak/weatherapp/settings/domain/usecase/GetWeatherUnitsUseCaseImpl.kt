package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnitType

class GetWeatherUnitsUseCaseImpl(
    private val repository: SettingsRepository
) : GetWeatherUnitsUseCase {
    override suspend fun execute(): List<WeatherUnit> {
        return listOf(
            repository.getUnit(WeatherUnitType.Temperature),
            repository.getUnit(WeatherUnitType.WindSpeed),
            repository.getUnit(WeatherUnitType.Precipitation),
        )
    }
}