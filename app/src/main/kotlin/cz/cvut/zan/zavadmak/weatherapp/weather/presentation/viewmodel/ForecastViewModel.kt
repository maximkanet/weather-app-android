package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.GetForecastUseCase
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatDate
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ForecastViewModel(
    private val longitude: Double,
    private val latitude: Double,
    private val getForecastUseCase: GetForecastUseCase
) : ViewModel() {

    private val _forecast = MutableStateFlow<Map<String, List<Weather>>>(mapOf())
    val forecast = _forecast.asStateFlow()

    private fun applyForecast(forecast: List<Weather>) {
        _forecast.update {
            forecast.groupBy(keySelector = { i -> i.time.formatDate() })
        }
    }

    private fun fetchForecast(
        longitude: Double,
        latitude: Double
    ) {
        viewModelScope.launch {
            applyForecast(getForecastUseCase.execute(lon = longitude, lat = latitude))
        }
    }

    init {
        fetchForecast(
            longitude = longitude,
            latitude = latitude
        )
    }
}