package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface WeatherScreens {

    @Serializable
    data class CurrentWeather(val locationId: Int) : WeatherScreens

    @Serializable
    data class Forecast(val locationId: Int) : WeatherScreens

}