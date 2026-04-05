package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState

@Composable
fun HourlyForecast(
    hourly: List<WeatherUiState>
) {
    WeatherCard(title = "24h forecast") {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(hourly) { item ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = item.temperature,
                        style = Typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Image(
                        painter = painterResource(item.icon),
                        contentDescription = item.codeString,
                        modifier = Modifier.size(28.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item.wind,
                        style = Typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Icon(
                        painter = painterResource(R.drawable.wind_direction_icon),
                        contentDescription = "Wind direction",
                        modifier = Modifier
                            .rotate(item.windDirection.toFloat())
                            .size(16.dp)
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        text = item.time,
                        style = Typography.bodySmall
                    )
                }
            }
        }
    }
}