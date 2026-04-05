package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model

data class DayHourlyUiState(
    val day: String,
    val hourly: List<WeatherUiState>
)