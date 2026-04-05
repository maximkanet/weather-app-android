package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.TopBarContainer

@Composable
fun CurrentWeatherTopBar(
    onHomeClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    TopBarContainer(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = onHomeClick) {
            Icon(
                painter = painterResource(R.drawable.outline_home_24),
                contentDescription = "Home button"
            )
        }
        IconButton(onClick = onSettingsClick) {
            Icon(
                painter = painterResource(R.drawable.outline_settings_24),
                contentDescription = "Settings button"
            )
        }
    }
}