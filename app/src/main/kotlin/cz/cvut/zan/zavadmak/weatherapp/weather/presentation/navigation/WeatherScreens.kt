package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface WeatherScreens {

    @Serializable
    data class CurrentWeather(val longitude: Double, val latitude: Double) : WeatherScreens

    @Serializable
    data class Forecast(val longitude: Double, val latitude: Double) : WeatherScreens

}