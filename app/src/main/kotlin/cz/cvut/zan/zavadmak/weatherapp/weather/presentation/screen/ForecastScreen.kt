package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component.ItemPicker
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DayHourlyUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState

@Composable
fun ForecastScreen(
    forecastRange: Int,
    onDaysChange: (Int) -> Unit,
    onBackBack: () -> Unit,
    location: Location?,
    forecast: List<DayHourlyUiState>,
) {
    Scaffold(
        topBar = {
            ForecastTopBar(
                forecastRange = forecastRange,
                onBackClick = onBackBack,
                onDaysChange = onDaysChange,
                location = location
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(forecast) { (day, hourly) ->
                Text(
                    text = day
                )
                Card {
                    LazyColumn {
                        items(hourly) {
                            HourlyItem(it)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HourlyItem(item: WeatherUiState) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = item.time,
            modifier = Modifier.weight(1f)
        )
        Icon(
            painter = painterResource(item.icon),
            contentDescription = item.codeString
        )
        Text(
            text = item.temperature,
            modifier = Modifier.weight(1f)
        )
        Row(
            modifier = Modifier.weight(1f)
        ) {
            Icon(
                painter = painterResource(R.drawable.wind_direction_icon),
                contentDescription = null,
                modifier = Modifier.rotate(item.windDirection.toFloat())
            )
            Text(
                text = item.time,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun ForecastTopBar(
    onBackClick: () -> Unit,
    forecastRange: Int,
    onDaysChange: (Int) -> Unit,
    location: Location?
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back"
                )
            }
            Text(
                text = location?.name ?: ""
            )
        }
        ItemPicker(
            current = forecastRange,
            items = listOf(5, 7, 10, 14),
            comparator = { i1, i2 -> i1 == i2 },
            toStringMapper = { i -> "$i days" },
            onItemSelect = onDaysChange
        )
    }
}