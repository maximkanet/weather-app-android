package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.TopBarContainer


@Composable
fun HomeScreenTopBar(
    onSettingsButtonClick: () -> Unit,
) {
    TopBarContainer(horizontalArrangement = Arrangement.End) {
        IconButton(
            onClick = onSettingsButtonClick
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_settings_24),
                contentDescription = "Settings button"
            )
        }
    }
}