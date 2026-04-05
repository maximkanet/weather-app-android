package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.ButtonWithIcon
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainerWithTitle
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.component.LocationComponentWithActionButton

@Composable
fun LocationsScreen(
    locations: List<Location>,
    onLocationRemove: (Location) -> Unit,
    onAddClick: () -> Unit
) {
    ScreenContainerWithTitle(
        title = "Locations",
        actionButton = {
            ButtonWithIcon(
                text = "",
                icon = R.drawable.loupe_icon,
                onClick = onAddClick,
                modifier = Modifier.sizeIn(
                    maxWidth = 32.dp,
                    maxHeight = 32.dp
                )
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(locations) { location ->
                LocationComponentWithActionButton(
                    locationName = location.name,
                    locationAddress = "${location.state}, ${location.country}"
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_circle_outline_24),
                        contentDescription = null,
                        modifier = Modifier.clickable(
                            onClick = {
                                onLocationRemove(location)
                            }
                        )
                    )
                }
            }
        }
    }
}
