package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.settings.data.provider.SettingsProvider
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetCurrentWeatherUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetDailyWeatherUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetHourlyWeatherUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.mapper.toUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CurrentWeatherViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    private val getHourlyWeatherUseCase: GetHourlyWeatherUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val settingsProvider: SettingsProvider,
    val locationId: Long,
) : ViewModel() {

    private val _location = MutableStateFlow<Location?>(null)
    val location = _location.asStateFlow()

    private fun fetchLocation(id: Long) {
        viewModelScope.launch {
            _location.update { getLocationUseCase.execute(id = id) }
        }
    }

    private val _weather = MutableStateFlow<WeatherUiState?>(null)
    val weather = _weather.asStateFlow()

    fun fetchCurrent(longitude: Double, latitude: Double) {
        viewModelScope.launch {
            val temperatureUnit = settingsProvider.getTemperatureUnit()
            val windUnit = settingsProvider.getWindUnit()
            val precipitationUnit = settingsProvider.getPrecipitationUnit()

            _weather.update {
                getCurrentWeatherUseCase
                    .execute(lon = longitude, lat = latitude)
                    .toUiState(
                        temperatureUnit = temperatureUnit,
                        windUnit = windUnit,
                        precipitationUnit = precipitationUnit
                    )
            }
        }
    }

    private val _daily = MutableStateFlow<List<DailyWeatherUiState>>(listOf())
    val daily = _daily.asStateFlow()

    fun fetchDaily(longitude: Double, latitude: Double) {
        viewModelScope.launch {
            val temperatureUnit = settingsProvider.getTemperatureUnit()

            _daily.update {
                getDailyWeatherUseCase
                    .execute(longitude = longitude, latitude = latitude)
                    .map { it.toUiState(temperatureUnit = temperatureUnit) }
            }

        }
    }

    private val _hourly = MutableStateFlow<List<WeatherUiState>>(listOf())
    val hourly = _hourly.asStateFlow()

    fun fetchHourly(longitude: Double, latitude: Double) {
        viewModelScope.launch {
            val temperatureUnit = settingsProvider.getTemperatureUnit()
            val windUnit = settingsProvider.getWindUnit()
            val precipitationUnit = settingsProvider.getPrecipitationUnit()

            _hourly.update {
                getHourlyWeatherUseCase.execute(
                    latitude = latitude,
                    longitude = longitude,
//                    period = DatePeriod(days = 2)
                )
                    .map {
                        it.toUiState(
                            temperatureUnit = temperatureUnit,
                            windUnit = windUnit,
                            precipitationUnit = precipitationUnit
                        )
                    }
            }
        }
    }

    fun fetchAll(latitude: Double, longitude: Double) {
        fetchCurrent(latitude = latitude, longitude = longitude)
        fetchDaily(latitude = latitude, longitude = longitude)
        fetchHourly(latitude = latitude, longitude = longitude)
    }

    init {
        fetchLocation(id = locationId)
    }

}