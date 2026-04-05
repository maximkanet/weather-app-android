package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen

import androidx.compose.runtime.Composable
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

@Deprecated("")
@Composable
fun LocationDetailsScreen(
    locationDetails: Location,
    onLocationDelete: (Long) -> Unit
) {
//    ScreenContainerWithTitle(
//        title = locationDetails.name,
//        actionButton = {
//            ButtonWithIcon(
//                text = "Remove",
//                onClick = {
//                    onLocationDelete(locationDetails.id)
//                }
//            )
//        }
//    ) {
//        Column {
//            Text(text = locationDetails.fullName)
//            Spacer(modifier = Modifier.height(10.dp))
//            Text(
//                text = "Latitude: ${locationDetails.latitude}, Longitude: ${locationDetails.longitude}"
//            )
//        }
//    }
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