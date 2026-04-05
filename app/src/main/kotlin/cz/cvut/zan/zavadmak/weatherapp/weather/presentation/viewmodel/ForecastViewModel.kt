package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.data.provider.SettingsProvider
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetForecastUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.mapper.toUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DayHourlyUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatDate
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatDateDay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val getForecastUseCase: GetForecastUseCase,
    private val getLocationUseCase: GetLocationUseCase,
    private val settingsProvider: SettingsProvider,
) : ViewModel() {

    private val _forecast = MutableStateFlow<List<DayHourlyUiState>>(listOf())
    val forecast = _forecast.asStateFlow()

    private fun applyForecast(
        forecast: List<Weather>,
        temperatureUnit: WeatherUnit,
        windUnit: WeatherUnit,
        precipitationUnit: WeatherUnit
    ) {
        _forecast.update {
            forecast.groupBy { it.time.date }
                .map { (_, weather) ->

                    val weatherDateTime = weather.first().time

                    val date = weatherDateTime.formatDate()
                    val weekDay = weatherDateTime.formatDateDay()

                    DayHourlyUiState(
                        day = "$date ($weekDay)",
                        hourly = weather.map {
                            it.toUiState(
                                temperatureUnit = temperatureUnit,
                                windUnit = windUnit,
                                precipitationUnit = precipitationUnit
                            )
                        }
                    )
                }
        }
    }

    fun fetchForecast(
        longitude: Double,
        latitude: Double,
        range: Int
    ) {
        viewModelScope.launch {
            val temperatureUnit = settingsProvider.getTemperatureUnit()
            val windUnit = settingsProvider.getWindUnit()
            val precipitationUnit = settingsProvider.getPrecipitationUnit()

            applyForecast(
                getForecastUseCase.execute(
                    longitude = longitude,
                    latitude = latitude,
                    range = range
                ),
                temperatureUnit = temperatureUnit,
                windUnit = windUnit,
                precipitationUnit = precipitationUnit,
            )
        }
    }

    private val _forecastRange = MutableStateFlow(5)
    val forecastRange = _forecastRange.asStateFlow()

    fun changeRange(range: Int) {
        _forecastRange.update { range }
    }

    private val _location = MutableStateFlow<Location?>(null)
    val location = _location.asStateFlow()

    fun fetchLocation(id: Long) {
        viewModelScope.launch {
            _location.update { getLocationUseCase.execute(id = id) }
        }
    }
}