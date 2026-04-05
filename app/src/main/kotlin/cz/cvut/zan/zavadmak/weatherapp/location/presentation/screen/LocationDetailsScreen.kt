package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.ButtonWithIcon
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainerWithTitle

@Composable
fun LocationDetailsScreen(
    locationDetails: Location,
    onLocationDelete: (Int) -> Unit
) {
    ScreenContainerWithTitle(
        title = locationDetails.name,
        actionButton = {
            ButtonWithIcon(
                text = "Remove",
                onClick = {
                    onLocationDelete(locationDetails.id)
                }
            )
        }
    ) {
        Column {
            Text(text = locationDetails.fullName)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Latitude: ${locationDetails.latitude}, Longitude: ${locationDetails.longitude}"
            )
        }
    }
}

//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun LocationDetailsScreenPreview() {
//    PreviewScreenContainer {
//        LocationDetailsScreen(
//            locationDetails = Location(
//                id = 9037,
//                longitude = 4.5,
//                latitude = 6.7,
//                shortName = "Earline Buck",
//                fullName = "Angelina Dickson"
//            ),
//            onLocationDelete = {}
//        )
//    }
//}