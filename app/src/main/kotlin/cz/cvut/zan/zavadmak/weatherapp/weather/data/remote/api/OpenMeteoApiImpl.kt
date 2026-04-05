package cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.api

import cz.cvut.zan.zavadmak.weatherapp.settings.data.provider.SettingsProvider
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnitType
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.CurrentWrapper
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.DailyDto
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.DailyWrapper
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.HourlyWrapper
import cz.cvut.zan.zavadmak.weatherapp.weather.data.remote.model.WeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

const val API_URL = "https://api.open-meteo.com/v1/forecast"

// wind_speed_unit=ms&temperature_unit=fahrenheit&precipitation_unit=inch

// API: https://open-meteo.com/
class OpenMeteoApiImpl(
    private val client: HttpClient,
    private val settingsProvider: SettingsProvider
) : MeteoApi {

    val weatherFields = listOf(
        "weather_code",
        "temperature_2m",
        "wind_speed_10m",
        "wind_gusts_10m",
        "wind_direction_10m",
        "relative_humidity_2m",
        "precipitation"
    )

    val dailyFields = listOf(
        "weather_code",
        "temperature_2m_max",
        "temperature_2m_min",
        "sunrise",
        "sunset"
    )

    override suspend fun getCurrentWeather(
        longitude: Double,
        latitude: Double
    ): WeatherDto {

        val temperatureUnit = settingsProvider.getTemperatureUnit()
        val windUnit = settingsProvider.getWindUnit()
        val precipitationUnit = settingsProvider.getPrecipitationUnit()

        return client
            .get(API_URL) {
                url {
                    parameters.append("latitude", latitude.toString())
                    parameters.append("longitude", longitude.toString())
                    parameters.append("temperature_unit", temperatureUnit.name)
                    parameters.append("wind_speed_unit", windUnit.name)
                    parameters.append("precipitation_unit", precipitationUnit.name)
                    parameters.append(
                        "current",
                        weatherFields.joinToString(separator = ",")
                    )
                    parameters.append("models", "best_match")
                }
            }
            .body<CurrentWrapper>()
            .current
    }

    override suspend fun getDailyWeather(
        longitude: Double,
        latitude: Double
    ): List<DailyDto> {

        val temperatureUnit = settingsProvider.getTemperatureUnit()
        val windUnit = settingsProvider.getWindUnit()
        val precipitationUnit = settingsProvider.getPrecipitationUnit()

        val response = client
            .get(API_URL) {
                url {
                    parameters.append("latitude", latitude.toString())
                    parameters.append("longitude", longitude.toString())
                    parameters.append("temperature_unit", temperatureUnit.name)
                    parameters.append("wind_speed_unit", windUnit.name)
                    parameters.append("precipitation_unit", precipitationUnit.name)
                    parameters.append("daily", dailyFields.joinToString(separator = ","))
                    parameters.append("models", "best_match")
                }
            }
            .body<DailyWrapper>()
            .daily
        return response.time.mapIndexed { index, time ->
            DailyDto(
                time = time,
                temperatureMin = response.temperatureMin[index],
                temperatureMax = response.temperatureMax[index],
                weatherCode = response.weatherCode[index],
                sunrise = response.sunrise[index],
                sunset = response.sunset[index]
            )
        }
    }

    override suspend fun getHourlyWeather(
        longitude: Double,
        latitude: Double,
        range: Int
    ): List<WeatherDto> {
        val temperatureUnit = settingsProvider.getTemperatureUnit()
        val windUnit = settingsProvider.getWindUnit()
        val precipitationUnit = settingsProvider.getPrecipitationUnit()

        val response = client
            .get(API_URL) {
                url {
                    parameters.append("latitude", latitude.toString())
                    parameters.append("longitude", longitude.toString())
                    parameters.append("temperature_unit", temperatureUnit.name)
                    parameters.append("wind_speed_unit", windUnit.name)
                    parameters.append("precipitation_unit", precipitationUnit.name)
                    parameters.append("forecast_days", range.toString())
                    parameters.append("models", "best_match")
                    parameters.append(
                        "hourly",
                        weatherFields.joinToString(separator = ",")
                    )
                }
            }
            .body<HourlyWrapper>()
            .hourly

        return response.time.mapIndexed { index, time ->
            WeatherDto(
                time = time,
                weatherCode = response.weatherCode[index],
                temperature = response.temperature[index],
                wind = response.wind[index],
                windGusts = response.windGusts[index],
                windDirection = response.windDirection[index],
                humidity = response.humidity[index],
                precipitation = response.precipitation[index],
            )
        }
    }
}