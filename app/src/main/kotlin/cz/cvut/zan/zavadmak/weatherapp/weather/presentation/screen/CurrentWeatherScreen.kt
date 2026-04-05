package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState

@Composable
fun CurrentWeatherScreen(
    weather: WeatherUiState?,
    location: Location?,
    dailyWeather: DailyWeatherUiState?,
    onDetailedForecastClick: (Location) -> Unit
) {

    ScreenContainer(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        WeatherDetails(
            location = location?.name ?: stringResource(R.string.unknown_location),
            currentTemperature = weather?.temperature ?: "",
            wind = weather?.wind ?: "",
            windGusts = weather?.windGusts ?: "",
            windDirection = weather?.windDirection ?: 0.0,
            humidity = weather?.humidity ?: "",
            precipitation = weather?.precipitation ?: "",
            weatherDescription = weather?.weatherCode ?: "",
            weatherIcon = weather?.weatherIcon ?: R.drawable.clear,
        )

        SunMovement(
            sunrise = dailyWeather?.sunrise ?: "-1:00",
            sunset = dailyWeather?.sunset ?: "-1:00",
            sunProgress = dailyWeather?.sunProgress ?: 0f,
        )

        Button(
            enabled = location != null,
            onClick = {
                if (location != null) {
                    onDetailedForecastClick(location)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.see_detailed_forecast))
        }
    }
}

@Composable
private fun WeatherDetails(
    location: String,
    currentTemperature: String,
    wind: String,
    windGusts: String,
    windDirection: Double,
    humidity: String,
    precipitation: String,
    weatherDescription: String,
    weatherIcon: Int,
) {

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(
                    text = location,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = currentTemperature,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                val infoFontSize = 16.sp

                Text(text = stringResource(R.string.wind) + ": $wind", fontSize = infoFontSize)
                Text(
                    text = stringResource(R.string.wind_gusts) + ": $windGusts",
                    fontSize = infoFontSize
                )
                Row {
                    Text(
                        text = stringResource(R.string.wind_direction) + ": ",
                        fontSize = infoFontSize
                    )
                    Icon(
                        painter = painterResource(R.drawable.wind_direction_icon),
                        contentDescription = stringResource(R.string.wind_direction_icon),
                        modifier = Modifier
                            .rotate(windDirection.toFloat())
                            .size(16.dp),
                    )
                }
                Text(
                    text = stringResource(R.string.humidity) + ": $humidity",
                    fontSize = infoFontSize
                )
                Text(
                    text = stringResource(R.string.precipitation) + ": $precipitation",
                    fontSize = infoFontSize
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(weatherIcon),
                contentDescription = stringResource(R.string.weather_code_icon),
                modifier = Modifier.size(60.dp)
            )
            Text(
                text = weatherDescription,
                fontSize = 16.sp,
            )
        }
    }
}

@Composable
private fun SunMovement(sunrise: String, sunset: String, sunProgress: Float) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(sunrise)
            Text(sunset)
        }
        LinearProgressIndicator(
            progress = { sunProgress },
            modifier = Modifier.fillMaxWidth(),
        )
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(stringResource(R.string.sunrise))
            Text(stringResource(R.string.sunset))
        }
    }
}