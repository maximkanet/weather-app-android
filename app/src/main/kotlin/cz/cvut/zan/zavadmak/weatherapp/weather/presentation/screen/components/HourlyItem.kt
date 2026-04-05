package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.weather.presentation.model.WeatherUiState

@Composable
fun HourlyItem(item: WeatherUiState) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = item.time,
            modifier = Modifier.weight(1f),
            style = Typography.bodySmall
        )
        Image(
            painter = painterResource(item.icon),
            contentDescription = item.codeString,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = item.temperature,
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = Typography.bodySmall
        )
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.wind,
                style = Typography.bodySmall,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Icon(
                painter = painterResource(R.drawable.wind_direction_icon),
                contentDescription = null,
                modifier = Modifier
                    .rotate(item.windDirection.toFloat())
                    .size(12.dp)
            )
        }
    }
}