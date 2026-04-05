package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetCurrentWeatherUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetDailyWeatherUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.mapper.toUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetWeatherUnitsUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class CurrentWeatherViewModel(
    private val longitude: Double,
    private val latitude: Double,
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase,
    private val getDailyWeatherUseCase: GetDailyWeatherUseCase,
    private val getWeatherUnitsUseCase: GetWeatherUnitsUseCase,
) : ViewModel() {

    private val _weatherUnits = MutableStateFlow<WeatherUnits>(WeatherUnits.asDefault())
    val weatherUnits = _weatherUnits.asStateFlow()

    private fun fetchWeatherUnits() {
        viewModelScope.launch {
            _weatherUnits.update {
                getWeatherUnitsUseCase.execute()
            }
        }
    }

    private val _weather = MutableStateFlow<WeatherUiState?>(null)
    val weather = _weather.asStateFlow()

    private fun applyWeather(weather: Weather) {
        _weather.update {
            weather.toUiState(weatherUnits.value)
        }
    }

    fun fetchCurrentWeather() {
        viewModelScope.launch {
            applyWeather(getCurrentWeatherUseCase.execute(lat = latitude, lon = longitude))
        }
    }

    private val _daily = MutableStateFlow<DailyWeatherUiState?>(null)
    val daily = _daily.asStateFlow()

    private fun applyDaily(dailyWeather: DailyWeather) {
        _daily.update {
            dailyWeather.toUiState(
                units = weatherUnits.value,
                now = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
            )
        }
    }

    fun fetchDailyWeather() {
        viewModelScope.launch {
            applyDaily(getDailyWeatherUseCase.execute(lat = latitude, lon = longitude).first())
        }
    }

    init {
        Log.d("CurrentWeatherViewModel", "Fetching weather from $longitude, $latitude")
        fetchWeatherUnits()
        fetchCurrentWeather() // Depends on weather units
        fetchDailyWeather() // Depends on weather units
    }

}