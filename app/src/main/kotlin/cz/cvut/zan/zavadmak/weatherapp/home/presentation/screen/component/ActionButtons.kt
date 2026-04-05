package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.LocationRequestUiState

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
        GetDeviceLocationButton(
            onGetLocationClick = onGetLocationClick,
            requestState = requestState,
        )
        Button(
            onClick = onSearchButtonClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_search_24),
                contentDescription = "Search button",
                modifier = Modifier.size(20.dp)
            )
        }
    }
}