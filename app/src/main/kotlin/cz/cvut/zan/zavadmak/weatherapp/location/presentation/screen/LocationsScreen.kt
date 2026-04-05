package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.ButtonWithIcon
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.PreviewScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainerWithTitle

@Composable
fun LocationsScreen(
    locations: List<Location>,
    onLocationClick: (Location) -> Unit,
    onLocationAdd: () -> Unit
) {
    ScreenContainerWithTitle(
        title = "Locations",
        actionButton = {
            ButtonWithIcon(
                text = "+",
                onClick = onLocationAdd
            )
        }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(locations) { location ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(onClick = { onLocationClick(location) })
                        .fillMaxWidth()
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            text = location.shortName,
                            fontSize = 20.sp
                        )
//                        Text(
//                            text = location.weatherInfo,
//                            fontSize = 16.sp
//                        )
                    }
//                    Text(
//                        text = location.currentTemperature,
//                        fontSize = 28.sp
//                    )
                    Text(
                        text = "see details",
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun LocationsScreenPreview() {
//    PreviewScreenContainer {
//        LocationsScreen(
//            locations = listOf(
//                Location.getDummyObject(),
//                Location.getDummyObject(),
//                Location.getDummyObject(),
//                Location.getDummyObject(),
//            ),
//            onLocationClick = {},
//            onLocationAdd = {}
//        )
//    }
//}
