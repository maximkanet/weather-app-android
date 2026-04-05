package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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

@Composable
fun WeatherDetails(
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