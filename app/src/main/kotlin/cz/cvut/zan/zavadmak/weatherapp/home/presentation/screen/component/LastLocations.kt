package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.content.LocationComponent

@Composable
fun LastLocations(
    onLocationClick: (Location) -> Unit,
    locations: List<Location>
) {
    Column {
        Text(
            text = stringResource(R.string.last_locations),
            style = Typography.titleLarge,
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