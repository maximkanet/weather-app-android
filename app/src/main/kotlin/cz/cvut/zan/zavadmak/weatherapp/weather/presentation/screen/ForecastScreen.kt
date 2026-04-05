package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

//import cz.cvut.zan.zavadmak.weatherapp.domain.model.WeatherFields
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainerWithTitle
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Purple80
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ForecastScreen(
    forecast: List<Pair<LocalDateTime, List<Weather>>>,
) {
    ScreenContainerWithTitle(
        title = "Forecast"
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(30.dp)) {
            items(forecast) { item ->
                Column {
                    Text(text = item.first.format(DateTimeFormatter.ofPattern("E (MM.dd)")))
                    Spacer(modifier = Modifier.height(10.dp))
                    ForecastTable(
                        forecast = item.second
                    )
                }
            }
        }
    }
}

@Composable
fun ForecastTable(
    forecast: List<Weather>
) {
    val columns = remember {
        listOf(
            "time",
            "",
            "t (℃)",
            "p (mm)",
            "w (m/s)",
            "w_d"
        )
    }

    val columnWeight = remember { 1f }

    Column {
        // Columns name
        Row {
            columns.forEach { name ->
                Text(
                    text = name,
                    fontSize = 10.sp,
                    modifier = Modifier
                        .weight(columnWeight)
                )
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            forecast.forEach { weatherFields ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
//                    Text(
//                        text = weatherFields.time.format(DateTimeFormatter.ofPattern("hh:mm")),
//                        modifier = Modifier.weight(columnWeight)
//                    )
                    Box(modifier = Modifier.weight(columnWeight)) {
                        Icon(
                            painter = painterResource(R.drawable.clear),
                            contentDescription = null,
                            modifier = Modifier
                                .size(16.dp),
                            tint = Purple80
                        )
                    }
                    Text(
                        text = weatherFields.temperature.toString(),
                        modifier = Modifier.weight(columnWeight)
                    )
                    Text(
                        text = weatherFields.precipitation.toString(),
                        modifier = Modifier.weight(columnWeight)
                    )
                    Text(
                        text = weatherFields.wind.toString(),
                        modifier = Modifier.weight(columnWeight)
                    )
                    Box(modifier = Modifier.weight(columnWeight)) {
                        Icon(
                            painter = painterResource(R.drawable.wind_direction_icon),
                            contentDescription = "Wind direction icon",
                            modifier = Modifier
                                .rotate(weatherFields.windDirection.toFloat())
                                .size(16.dp),
                            tint = Color(0xFF135CD2)
                        )
                    }
                }
            }
        }
    }
}


//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun ForecastScreenPreview() {
//    PreviewScreenContainer {
//        ForecastScreen(
//            forecast = listOf(
//                Pair(
//                    LocalDateTime.of(2025, 4, 25, 5, 0),
//                    WeatherFields.createDummyForecast()
//                ),
//                Pair(
//                    LocalDateTime.of(2025, 4, 26, 5, 0),
//                    WeatherFields.createDummyForecast()
//                ),
//                Pair(
//                    LocalDateTime.of(2025, 4, 27, 5, 0),
//                    WeatherFields.createDummyForecast()
//                )
//            ),
//        )
//    }
//}