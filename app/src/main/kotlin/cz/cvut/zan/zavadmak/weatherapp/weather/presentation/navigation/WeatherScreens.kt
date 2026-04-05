package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface WeatherScreens {

    @Serializable
    data class CurrentWeather(val id: Long) : WeatherScreens

    @Serializable
    data class Forecast(val id: Long) : WeatherScreens

}