package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState

@Composable
fun CurrentWeatherScreen(
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit,
    location: Location,
    currentWeather: WeatherUiState,
    daily: List<DailyWeatherUiState>,
    hourly: List<WeatherUiState>,
) {
//    Scaffold(
//        topBar = {
//            CurrentWeatherTopBar(
//                onHomeClick = onHomeClick,
//                onSettingsClick = onSettingsClick
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier.padding(paddingValues)
//        ) {
//            WeatherSummary(
//                location = location,
//                currentWeather = currentWeather,
//            )
//            DailyForecast(
//                daily = daily
//            )
//            HourlyForecast(
//                hourly = hourly
//            )
//        }
//    }
}

@Composable
fun CurrentWeatherTopBar(
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onHomeClick) {
            Icon(
                painter = painterResource(R.drawable.outline_home_24),
                contentDescription = null
            )
        }
        IconButton(onClick = onSettingsClick) {
            Icon(
                painter = painterResource(R.drawable.outline_settings_24),
                contentDescription = null
            )
        }
    }
}

@Composable
fun WeatherSummary(
    location: Location,
    currentWeather: WeatherUiState,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = location.name
            )
            Text(
                text = currentWeather.temperature
            )
        }
        Column {
            Icon(
                painter = painterResource(currentWeather.icon),
                contentDescription = currentWeather.codeString,
            )
            Text(
                text = currentWeather.codeString
            )
        }
    }
}

@Composable
fun DailyForecast(
    daily: List<DailyWeatherUiState>,
    onDetailedForecastClick: () -> Unit,
    forecastButtonEnabled: Boolean,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Text(
                text = "Forecast"
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(daily) { weather ->
                    Text(
                        text = weather.day,
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        painter = painterResource(weather.icon),
                        contentDescription = weather.codeString,
                    )
                    Text(
                        text = "${weather.temperatureMin} / ${weather.temperatureMax}",
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            Button(
                enabled = forecastButtonEnabled,
                onClick = onDetailedForecastClick
            ) {
                Text(
                    text = stringResource(R.string.see_detailed_forecast)
                )
            }
        }
    }
}

@Composable
fun HourlyForecast(
    hourly: List<WeatherUiState>
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.padding(16.dp)
    ) {
        Column {
            Text(
                text = "24h forecast"
            )
            LazyRow {
                items(hourly) { item ->
                    Column {
                        Text(
                            text = item.temperature
                        )
                        Icon(
                            painter = painterResource(item.icon),
                            contentDescription = item.codeString
                        )
                        Text(
                            text = item.wind
                        )
                        Icon(
                            painter = painterResource(R.drawable.wind_direction_icon),
                            contentDescription = "Wind direction",
                            modifier = Modifier.rotate(item.windDirection.toFloat())
                        )
                        Text(
                            text = item.time
                        )
                    }
                }

            }
        }
    }
}