package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DailyWeatherUiState

@Composable
fun DailyForecast(
    daily: List<DailyWeatherUiState>,
    onDetailedForecastClick: () -> Unit,
    forecastButtonEnabled: Boolean,
) {
    WeatherCard(title = "Forecast") {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            daily.forEach { weather ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = weather.day,
                        modifier = Modifier.weight(1f),
                        style = Typography.bodySmall
                    )
                    Image(
                        painter = painterResource(weather.icon),
                        contentDescription = weather.codeString,
                        modifier = Modifier.size(22.dp)
                    )
                    Text(
                        text = "${weather.temperatureMin} / ${weather.temperatureMax}",
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End,
                        style = Typography.bodySmall
                    )
                }
            }
        }
        Button(
            enabled = forecastButtonEnabled,
            onClick = onDetailedForecastClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.see_detailed_forecast)
            )
        }
    }
}