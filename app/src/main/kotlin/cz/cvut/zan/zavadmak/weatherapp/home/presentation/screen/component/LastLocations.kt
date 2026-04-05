package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.ScreenMode
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.component.LocationComponent
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.component.LocationSelectableComponent

@Composable
fun LastLocations(
    onLocationsRemove: (Set<Long>) -> Unit,
    onLocationClick: (Location) -> Unit,
    locations: List<Location>
) {
    var mode by remember { mutableStateOf(ScreenMode.IDLE) }
    var selectedItems by remember { mutableStateOf(setOf<Long>()) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.last_locations),
                style = Typography.titleLarge,
                modifier = Modifier.weight(1f)
            )
            if (mode == ScreenMode.SELECTION) {
                IconButton(
                    onClick = {
                        onLocationsRemove(selectedItems)
                        mode = ScreenMode.IDLE
                        selectedItems = setOf()
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_remove_circle_outline_24),
                        contentDescription = null,
//                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            items(items = locations) { location ->
                if (mode == ScreenMode.SELECTION) {
                    var selected by remember { mutableStateOf(false) }
                    LocationSelectableComponent(
                        onCheckedChange = {
                            selectedItems = if (it)
                                selectedItems + location.id
                            else
                                selectedItems - location.id

                            selected = it
                        },
                        selected = selected,
                        name = location.name,
                        state = location.state,
                        country = location.country
                    )
                } else if (mode == ScreenMode.IDLE) {
                    LocationComponent(
                        onClick = {
                            onLocationClick(location)
                        },
                        onLongClick = {
                            mode = ScreenMode.SELECTION
                            Log.d("LastLocations", "Mode switched to SELECTION")
                        },
                        name = location.name,
                        state = location.state,
                        country = location.country
                    )
                }
            }
        }
    }
}