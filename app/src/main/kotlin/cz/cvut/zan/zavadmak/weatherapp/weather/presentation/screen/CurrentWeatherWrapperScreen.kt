package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components.CurrentWeatherTopBar
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components.DailyForecast
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components.HourlyForecast
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components.WeatherSummary
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@Composable
fun CurrentWeatherWrapperScreen(
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit,
    location: Location?,
    currentWeather: WeatherUiState?,
    daily: List<DailyWeatherUiState>?,
    onDetailedForecastClick: (Location) -> Unit,
    hourly: List<WeatherUiState>?,
) {
    ScreenContainer(
        topBar = {
            CurrentWeatherTopBar(
                onHomeClick = onHomeClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) {
        if (currentWeather != null && location != null) {
            WeatherSummary(
                location = location,
                currentWeather = currentWeather,
            )
        } else {
            DummyWeatherSummary()
        }

        Spacer(modifier = Modifier.height(30.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            if (daily != null && location != null)
                DailyForecast(
                    daily = daily,
                    onDetailedForecastClick = {
                        onDetailedForecastClick(location)
                    },
                    forecastButtonEnabled = true
                )
            else
                DummyDailyForecast()

            if (hourly != null)
                HourlyForecast(
                    hourly = hourly
                )
            else
                DummyHourlyForecast()
        }
    }
}

@Composable
fun DummyWeatherSummary() {
    WeatherSummary(
        location = Location(
            id = 0,
            longitude = 0.0,
            latitude = 0.0,
            name = "Location ...",
            state = "---",
            country = "---",
            lastUse = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        ),
        currentWeather = WeatherUiState(
            day = "---",
            time = "---",
            codeString = "---",
            icon = R.drawable.app_icon, // add gray icon
            temperature = "---",
            wind = "---",
            windGusts = "---",
            windDirection = 0.0,
            humidity = "---",
            precipitation = "---"
        )
    )
}

@Composable
fun DummyDailyForecast() {
    DailyForecast(
        daily = listOf(),
        onDetailedForecastClick = {},
        forecastButtonEnabled = false
    )
}

@Composable
fun DummyHourlyForecast() {
    HourlyForecast(
        hourly = listOf()
    )
}