package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography

@Composable
fun WeatherCard(
    title: String,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    cornerRadius: Dp = 16.dp,
    padding: PaddingValues = PaddingValues(24.dp),
    content: @Composable () -> Unit,
) {
    Column(
        verticalArrangement = verticalArrangement,
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(cornerRadius),
            )
            .padding(padding)
            .fillMaxWidth()
    ) {
        Text(
            text = title,
            style = Typography.titleMedium
        )
        content()
    }
}