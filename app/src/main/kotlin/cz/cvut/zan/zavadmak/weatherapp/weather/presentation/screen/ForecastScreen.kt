package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.Table
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.TableCell
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.TableRow
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.TableRows
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.PreviewScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainerWithTitle
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.utils.itemGroups
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.Weather
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.model.WeatherUnits
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.utils.formatTime
import kotlinx.datetime.LocalDateTime

@Composable
fun ForecastScreen(
    weatherUnits: WeatherUnits,
    forecast: Map<String, List<Weather>>,
) {
    ScreenContainerWithTitle(
        title = "Forecast"
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(30.dp)) {
            itemGroups(forecast) { date, forecastItems ->
                Column {
                    Text(text = date)
                    Spacer(modifier = Modifier.height(10.dp))
                    Table(
                        columns = {
                            val colNameTextSize by remember { mutableStateOf(10.sp) }

                            TableCell(weight = .7f) {
                                Text(
                                    text = "time",
                                    fontSize = colNameTextSize
                                )
                            }
                            TableCell {
                                Text(
                                    text = "",
                                    fontSize = colNameTextSize
                                )
                            }
                            TableCell {
                                Text(
                                    text = "t (${weatherUnits.temperature})",
                                    fontSize = colNameTextSize
                                )
                            }
                            TableCell {
                                Text(
                                    text = "w (${weatherUnits.speed})",
                                    fontSize = colNameTextSize
                                )
                            }
                            TableCell {
                                Text(
                                    text = "w_g (${weatherUnits.speed})",
                                    fontSize = colNameTextSize
                                )
                            }
                            TableCell {
                                Text(
                                    text = "w_d (${weatherUnits.degree})",
                                    fontSize = colNameTextSize
                                )
                            }
                        },
                    ) {
                        TableRows(forecastItems) { row ->
                            TableRow {
                                TableCell(weight = .7f) {
                                    Text(row.time.formatTime())
                                }
                                TableCell(
                                    horizontalArrangement = Arrangement.Center,
                                ) {
                                    Icon(
                                        painter = painterResource(R.drawable.clear),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(16.dp)
                                    )
                                }
                                TableCell {
                                    Text(text = row.temperature.toInt().toString())
                                }
                                TableCell {
                                    Text(text = row.wind.toInt().toString())
                                }
                                TableCell {
                                    Text(text = row.windGusts.toInt().toString())
                                }
                                TableCell {
                                    Icon(
                                        painter = painterResource(R.drawable.wind_direction_icon),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .rotate(row.windDirection.toFloat())
                                            .size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(device = PIXEL_7_PRO)
@Composable
fun ForecastScreenPreview() {
    PreviewScreenContainer {
        ForecastScreen(
            weatherUnits = WeatherUnits.asDefault(),
            forecast = mapOf(
                Pair(
                    "Thu (xx:xx)",
                    listOf(
                        Weather(
                            time = LocalDateTime.parse("2025-04-25T16:00"),
                            weatherCode = 6505,
                            temperature = 42.43,
                            wind = 44.45,
                            windGusts = 46.47,
                            windDirection = 48.49,
                            humidity = 50.51,
                            precipitation = 52.53,
                        ),
                        Weather(
                            time = LocalDateTime.parse("2025-04-25T17:00"),
                            weatherCode = 6505,
                            temperature = 42.43,
                            wind = 44.45,
                            windGusts = 46.47,
                            windDirection = 48.49,
                            humidity = 50.51,
                            precipitation = 52.53,
                        )
                    )
                )
            )
        )
    }
}