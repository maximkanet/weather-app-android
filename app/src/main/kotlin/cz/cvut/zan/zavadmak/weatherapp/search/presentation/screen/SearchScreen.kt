package cz.cvut.zan.zavadmak.weatherapp.search.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.TopBarContainer
import cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.component.LocationComponent
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    result: List<Location>?,
    onSearch: (String) -> Unit,
    onLocationClick: (Location) -> Unit,
    onBack: () -> Unit
) {
    ScreenContainer(
        topBar = {
            TopBarContainer(modifier = Modifier.padding(top = 5.dp)) {
                Box(
                    modifier = Modifier
                        .background(
                            shape = RoundedCornerShape(32.dp),
                            color = MaterialTheme.colorScheme.surfaceContainer
                        )
                ) {
                    InputField(onSearch = onSearch, onBack = onBack)
                }
            }
        },
        modifier = Modifier.padding(top = 20.dp)
    ) {
        if (result != null) {
            if (result.isNotEmpty()) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(result) { location ->
                        LocationComponent(
                            onClick = { onLocationClick(location) },
                            name = location.name,
                            state = location.state,
                            country = location.country,
                        )
                    }
                }
            } else {
                ResultListIsEmpty()
            }
        } else {
            ResultListPlaceholder()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    onSearch: (String) -> Unit,
    onBack: () -> Unit
) {
    var query by remember { mutableStateOf("") }

    SearchBarDefaults.InputField(
        query = query,
        onQueryChange = { query = it },
        onSearch = { onSearch(query) },
        placeholder = { Text("For example: Paris") },
        expanded = false,
        onExpandedChange = {},
        modifier = Modifier
            .sizeIn(minHeight = 25.dp),
        leadingIcon = {
            IconButton(
                onClick = onBack
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back button"
                )
            }
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(
                    onClick = { query = "" }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.baseline_clear_24),
                        contentDescription = "Clear button"
                    )
                }
            }
        }
    )
}

@Composable
fun ResultListPlaceholder() {
    Text(
        text = stringResource(R.string.query_is_empty),
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun ResultListIsEmpty() {
    Text(
        text = stringResource(R.string.result_list_is_empty),
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun SearchScreenPreview() {
//    PreviewScreenContainer {
//        SearchScreen(
//            result = listOf(
//                Location(
//                    id = 1,
//                    longitude = 12.0,
//                    latitude = 14.0,
//                    name = "Nova Vodolaha",
//                    state = "Kharkiv district",
//                    country = "Ukraine",
//                ),
//                Location(
//                    id = 1,
//                    longitude = 12.0,
//                    latitude = 14.0,
//                    name = "Nova Vodolaha",
//                    state = "Kharkiv district",
//                    country = "Ukraine",
//                ),
//                Location(
//                    id = 1,
//                    longitude = 12.0,
//                    latitude = 14.0,
//                    name = "Nova Vodolaha",
//                    state = "Kharkiv district",
//                    country = "Ukraine",
//                )
//            ),
//            onSearch = {},
//            onLocationClick = {},
//            onBack = {}
//        )
//    }
//}