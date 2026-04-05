package cz.cvut.zan.zavadmak.weatherapp.weather.mapper

import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity.LocationEntity
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.weather.data.local.entity.WeatherEntity
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.DailyDto
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.WeatherDto
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.DailyWeather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherRequest
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherRequestUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.calculateProgress
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatDate
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatDateDay
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatTime
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.weatherCodeToDrawable
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.weatherCodeToString
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun Weather.toUiState(
    temperatureUnit: WeatherUnit,
    windUnit: WeatherUnit,
    precipitationUnit: WeatherUnit
): WeatherUiState {
    return WeatherUiState(
        temperature = temperatureUnit.valueToString(this.temperature),
        wind = windUnit.valueToString(this.wind),
        windGusts = windUnit.valueToString(this.windGusts),
        windDirection = this.windDirection,
        humidity = "${this.humidity} %",
        precipitation = precipitationUnit.valueToString(this.precipitation),
        codeString = weatherCodeToString(this.weatherCode),
        icon = weatherCodeToDrawable(this.weatherCode),
        day = this.time.formatDate(),
        time = this.time.formatTime(),
    )
}

fun DailyWeather.toUiState(
    temperatureUnit: WeatherUnit,
): DailyWeatherUiState {
    return DailyWeatherUiState(
        codeString = weatherCodeToString(this.weatherCode),
        icon = weatherCodeToDrawable(this.weatherCode),
        temperatureMin = temperatureUnit.valueToString(this.temperatureMin),
        temperatureMax = temperatureUnit.valueToString(this.temperatureMax),
        sunrise = this.sunrise.formatTime(),
        sunset = this.sunset.formatTime(),
        day = this.time.formatDateDay(),
        date = this.time.formatDate(),
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

fun WeatherEntity.toDomainModel(): Weather {
    return Weather(
        time = Instant.fromEpochSeconds(this.time).toLocalDateTime(TimeZone.currentSystemDefault()),
        weatherCode = this.weatherCode,
        temperature = this.temperature,
        wind = this.wind,
        windGusts = this.windGusts,
        windDirection = this.windDirection,
        humidity = this.humidity,
        precipitation = this.precipitation

    )
}

fun Weather.toDataModel(location: LocationEntity): WeatherEntity {
    return WeatherEntity(
        placeId = location.placeId,
        time = this.time.toInstant(UtcOffset.ZERO).toEpochMilliseconds(),
        temperature = this.temperature,
        weatherCode = this.weatherCode,
        wind = this.wind,
        windGusts = this.windGusts,
        windDirection = this.windDirection,
        humidity = this.humidity,
        precipitation = this.precipitation
    )
}

fun WeatherRequest.toUiState(): WeatherRequestUiState {
    return when (this) {
        WeatherRequest.IDLE -> WeatherRequestUiState(
            request = this,
            stringRes = R.string.request_idle
        )

        WeatherRequest.SUCCESS -> WeatherRequestUiState(
            request = this,
            stringRes = R.string.request_success
        )

        WeatherRequest.ERROR -> WeatherRequestUiState(
            request = this,
            stringRes = R.string.request_error
        )

        WeatherRequest.UPDATING -> WeatherRequestUiState(
            request = this,
            stringRes = R.string.request_updating
        )
    }
}