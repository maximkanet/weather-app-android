package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle.Companion.Italic
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.LocationRequestUiState
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component.AppLogo
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component.GetMyLocationButton
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component.LocationComponent
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

@Composable
fun HomeScreen(
    onGetLocationClick: () -> Unit,
    locationRequestState: LocationRequestUiState,
    onSearchButtonClick: () -> Unit,
    onSettingsButtonClick: () -> Unit,
    onLocationClick: (Location) -> Unit,
    lastLocations: List<Location>,
) {
    Scaffold(
        topBar = {
            HomeScreenTopBar(
                onSettingsButtonClick = onSettingsButtonClick
            )
        }
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(30.dp),
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth()
        ) {
            AppLogo()
            ActionButtons(
                onSearchButtonClick = onSearchButtonClick,
                onGetLocationClick = onGetLocationClick,
                requestState = locationRequestState
            )
            if (lastLocations.isNotEmpty()) {
                LastLocations(
                    locations = lastLocations,
                    onLocationClick = onLocationClick
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
}

@Composable
fun ActionButtons(
    onSearchButtonClick: () -> Unit,
    onGetLocationClick: () -> Unit,
    requestState: LocationRequestUiState
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(
            10.dp,
            alignment = Alignment.CenterHorizontally
        ),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        GetMyLocationButton(
            onGetLocationClick = onGetLocationClick,
            requestState = requestState,
        )
        Button(
            onClick = onSearchButtonClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_search_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    }
}

@Composable
fun LastLocations(
    onLocationClick: (Location) -> Unit,
    locations: List<Location>
) {
    Column(
        modifier = Modifier.padding(horizontal = 40.dp)
    ) {
        Text(
            text = "Last locations",
            fontSize = 20.sp
        )
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(items = locations) { location ->
                LocationComponent(
                    onClick = {
                        onLocationClick(location)
                    },
                    name = location.name,
                    state = location.state,
                    country = location.country
                )
            }
        }
    }
}

@Composable
fun HomeScreenTopBar(
    onSettingsButtonClick: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        IconButton(
            onClick = onSettingsButtonClick
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_settings_24),
                contentDescription = null
            )
        }

    }
}

//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun HomeScreenPreview() {
//    HomeScreen(
//        onLocationClick = {},
//        lastLocations = listOf(
//            Location(
//                id = 0,
//                longitude = 0.0,
//                latitude = 0.0,
//                name = "Nova vodolaha",
//                state = "Kharkiv oblast",
//                country = "Ukraine"
//
//            ),
//            Location(
//                id = 0,
//                longitude = 0.0,
//                latitude = 0.0,
//                name = "Nova vodolaha",
//                state = "Kharkiv oblast",
//                country = "Ukraine"
//
//            ),
//            Location(
//                id = 0,
//                longitude = 0.0,
//                latitude = 0.0,
//                name = "Nova vodolaha",
//                state = "Kharkiv oblast",
//                country = "Ukraine"
//
//            ),
//            Location(
//                id = 0,
//                longitude = 0.0,
//                latitude = 0.0,
//                name = "Nova vodolaha",
//                state = "Kharkiv oblast",
//                country = "Ukraine"
//
//            ),
//            Location(
//                id = 0,
//                longitude = 0.0,
//                latitude = 0.0,
//                name = "Nova vodolaha",
//                state = "Kharkiv oblast",
//                country = "Ukraine"
//
//            )
//        )
//    )
//}