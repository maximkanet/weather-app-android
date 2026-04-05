package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherRequest
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherRequestUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState

@Composable
fun CurrentWeatherWrapperScreen(
    request: WeatherRequestUiState,
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit,
    location: Location?,
    currentWeather: WeatherUiState?,
    daily: List<DailyWeatherUiState>?,
    onDetailedForecastClick: (Location) -> Unit,
    hourly: List<WeatherUiState>?,
) {
    Scaffold(
        topBar = {
            CurrentWeatherTopBar(
                onHomeClick = onHomeClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            var requestState = ""

            if(request.request != WeatherRequest.IDLE) {
                requestState = stringResource(request.stringRes)
            }

            Text(
                text = requestState,
                modifier = Modifier.fillMaxWidth()
            )
            if (currentWeather != null && location != null) {
                WeatherSummary(
                    location = location,
                    currentWeather = currentWeather,
                )
            } else {
                DummyWeatherSummary()
            }

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
            name = "---",
            state = "---",
            country = "---"
        ),
        currentWeather = WeatherUiState(
            day = "perpetua",
            time = "aperiri",
            codeString = "evertitur",
            icon = R.drawable.app_icon,
            temperature = "suas",
            wind = "veniam",
            windGusts = "expetendis",
            windDirection = 2.3,
            humidity = "malesuada",
            precipitation = "justo"
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