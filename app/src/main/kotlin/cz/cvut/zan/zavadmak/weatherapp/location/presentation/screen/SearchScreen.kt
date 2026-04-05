package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
//import cz.cvut.zan.zavadmak.weatherapp.presentation.model.RequestState
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.PreviewScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainerWithTitle

@Composable
fun SearchScreen(
    result: List<Location>,
    onSearchFieldChanged: (String) -> Unit,
    onLocationClick: (Location) -> Unit,
    onBackButtonClick: () -> Unit,
//    requestState: RequestState,
) {

    ScreenContainerWithTitle(
        title = "Search"
    ) {
        Row(
            modifier = Modifier
        ) {
            TextField(
                "",
                onValueChange = { value ->
                    onSearchFieldChanged(value)
                },
                placeholder = {
                    Text("Type the city name ...")
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

//        when (requestState) {
//            is RequestState.Loading -> LoadingText()
//            is RequestState.Result -> ResultList(result, onLocationClick = onLocationClick)
//            is RequestState.Error -> ErrorTextWithBackButton(onBackButtonClick = onBackButtonClick)
//        }
    }
}

@Composable
private fun LoadingText() {
    Text(
        text = "Loading ...",
        textAlign = TextAlign.Center,
        fontSize = 16.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun ErrorTextWithBackButton(
    onBackButtonClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Request ended with error",
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onBackButtonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back to locations")
        }
    }
}

@Composable
private fun ResultList(
    results: List<Location>,
    onLocationClick: (Location) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(results) { location ->
            Column(
                modifier = Modifier.clickable(onClick = { onLocationClick(location) })
            ) {
                Text(
                    text = location.shortName,
                    fontSize = 16.sp
                )
                Text(
                    text = location.fullName,
                    color = Color(0xFF4E4E4E),
                    fontSize = 14.sp
                )
            }
        }
    }
}

//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun SearchScreenPreview() {
//    PreviewScreenContainer {
//        SearchScreen(
//            result = listOf(
//                Location.getDummyObject(),
//                Location.getDummyObject(),
//                Location.getDummyObject(),
//            ),
//            onSearchFieldChanged = {},
//            onLocationClick = {},
//            onBackButtonClick = {},
//            requestState = RequestState.Result()
//        )
//    }
//}