package cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.settings.data.repository.SettingsRepository
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit

class SetWeatherUnitUseCaseImpl(
    private val repository: SettingsRepository
) : SetWeatherUnitUseCase {
    override suspend fun execute(unit: WeatherUnit) {
        repository.setUnit(unit)
    }
}