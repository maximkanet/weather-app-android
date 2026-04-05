package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface WeatherScreens {

    @Serializable
    data class CurrentWeather(val locationId: Long) : WeatherScreens

    @Serializable
    data class Forecast(val locationId: Long) : WeatherScreens

}