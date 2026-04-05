package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState

@Composable
fun WeatherSummary(
    location: Location,
    currentWeather: WeatherUiState,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = location.name,
                style = Typography.bodyMedium
            )
            Text(
                text = currentWeather.temperature,
                style = Typography.headlineLarge
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(currentWeather.icon),
                contentDescription = currentWeather.codeString,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = currentWeather.codeString,
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}
