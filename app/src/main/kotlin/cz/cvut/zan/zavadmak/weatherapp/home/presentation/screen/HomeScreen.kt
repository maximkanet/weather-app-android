package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.LocationRequestUiState
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.ScreenMode
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component.ActionButtons
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component.AppLogo
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component.HomeScreenTopBar
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component.LastLocations
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

@Composable
fun HomeScreen(
    onGetLocationClick: () -> Unit,
    locationRequestState: LocationRequestUiState,
    onSearchButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    onLocationsRemove: () -> Unit,
    onLocationLongClick: (Long) -> Unit,
    mode: ScreenMode,
    initiatorId: Long,
    onLocationChangeCheked: (Long, Boolean) -> Unit,
    onLocationClick: (Location) -> Unit,
    lastLocations: List<Location>,
) {
    ScreenContainer(
        topBar = {
            HomeScreenTopBar(onSettingsButtonClick = onSettingsButtonClick)
        },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(30.dp),
    ) {
        AppLogo()
        ActionButtons(
            onSearchButtonClick = onSearchButtonClick,
            onGetLocationClick = onGetLocationClick,
            requestState = locationRequestState
        )
        if (lastLocations.isNotEmpty()) {
            LastLocations(
                onLocationsRemove = onLocationsRemove,
                locations = lastLocations,
                onLocationClick = onLocationClick,
                onCheckedChange = onLocationChangeCheked,
                initiatorId = initiatorId,
                mode = mode,
                onLocationLongClick = onLocationLongClick,
            )
        } else {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = stringResource(R.string.last_locations_placeholder),
                fontStyle = Italic,
                color = Color(0xFF525252),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
