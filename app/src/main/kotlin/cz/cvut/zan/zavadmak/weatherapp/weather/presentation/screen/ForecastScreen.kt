package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.WeatherAppTheme
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.DayHourlyUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components.ForecastTopBar
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components.HourlyItem

@Composable
fun ForecastScreen(
    forecastRange: Int,
    onDaysChange: (Int) -> Unit,
    onBackBack: () -> Unit,
    location: Location?,
    forecast: List<DayHourlyUiState>,
) {
    ScreenContainer(
        topBar = {
            ForecastTopBar(
                onBackClick = onBackBack,
                forecastRange = forecastRange,
                onDaysChange = onDaysChange,
                location = location,
            )
        },
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(forecast) { (day, hourly) ->
                Column {
                    Text(
                        text = day,
                        style = Typography.titleSmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Column(
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .background(
                                shape = RoundedCornerShape(10.dp),
                                color = MaterialTheme.colorScheme.surfaceContainer
                            )
                            .padding(15.dp)
                    ) {
                        hourly.forEach {
                            HourlyItem(it)
                        }
                    }
                }
            }
        }
    }
}

//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun ForecastScreenPreview() {
//    WeatherAppTheme(darkTheme = true) {
//        ForecastScreen(
//            forecastRange = 7,
//            onDaysChange = {},
//            onBackBack = {},
//            location = Location(
//                id = 0,
//                longitude = 12.0,
//                latitude = 14.0,
//                name = "Nova vodolaha ldlfd lfd ksdm fsdfsd dsfdsffsd dsfdsf",
//                state = "Kharkiv",
//                country = "Ukraine"
//            ),
//            forecast = listOf(
//                DayHourlyUiState(
//                    day = "Ut (23.02)",
//                    hourly = listOf(
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        )
//                    )
//                ),
//                DayHourlyUiState(
//                    day = "Ut (23.02)",
//                    hourly = listOf(
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        ),
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        ),
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        )
//                    )
//                ),
//                DayHourlyUiState(
//                    day = "Ut (23.02)",
//                    hourly = listOf(
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        ),
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        ),
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        ),
//                        WeatherUiState(
//                            day = "",
//                            time = "00:00",
//                            codeString = "CLear",
//                            icon = R.drawable.app_icon,
//                            temperature = WeatherUnit.Celsius().valueToString(10.0),
//                            wind = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windGusts = WeatherUnit.MetersPerSecond().valueToString(10.0),
//                            windDirection = 30.0,
//                            humidity = "12 %",
//                            precipitation = WeatherUnit.Millimeters().valueToString(10.0)
//                        )
//                    )
//                )
//            )
//        )
//    }
//}