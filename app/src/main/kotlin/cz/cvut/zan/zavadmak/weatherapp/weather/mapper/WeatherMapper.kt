package cz.cvut.zan.zavadmak.weatherapp.weather.mapper

import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.DailyDto
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.WeatherDto
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.calculateProgress
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatTime
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.weatherCodeToDrawable
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.weatherCodeToString
import kotlinx.datetime.LocalDateTime

fun Weather.toUiState(units: WeatherUnits): WeatherUiState {
    return WeatherUiState(
        temperature = units.temperature(this.temperature),
        wind = units.speed(this.wind),
        windGusts = units.speed(this.windGusts),
        windDirection = this.windDirection,
        humidity = units.percents(this.humidity),
        precipitation = units.precipitation(this.precipitation),
        weatherCode = weatherCodeToString(this.weatherCode),
        weatherIcon = weatherCodeToDrawable(this.weatherCode),
    )
}

fun DailyWeather.toUiState(units: WeatherUnits, now: LocalDateTime): DailyWeatherUiState {
    return DailyWeatherUiState(
        weatherCode = weatherCodeToString(this.weatherCode),
        weatherIcon = weatherCodeToDrawable(this.weatherCode),
        temperatureMin = units.temperature(this.temperatureMin),
        temperatureMax = units.temperature(this.temperatureMax),
        sunrise = this.sunrise.formatTime(),
        sunset = this.sunset.formatTime(),
        sunProgress = calculateProgress(now, this.sunrise, this.sunset)
    )
}

fun DailyDto.toDomainModel(): DailyWeather {
    return DailyWeather(
        time = LocalDateTime.parse(this.sunrise),
        sunrise = LocalDateTime.parse(this.sunrise),
        sunset = LocalDateTime.parse(this.sunset),
        temperatureMin = this.temperatureMin,
        temperatureMax = this.temperatureMax,
        weatherCode = this.weatherCode
    )
}

fun WeatherDto.toDomainModel(): Weather {
    return Weather(
        time = LocalDateTime.parse(this.time),
        weatherCode = this.weatherCode,
        temperature = this.temperature,
        wind = this.wind,
        windGusts = this.windGusts,
        windDirection = this.windDirection,
        humidity = this.humidity,
        precipitation = this.precipitation
    )
}