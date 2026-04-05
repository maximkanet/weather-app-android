package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices.PIXEL_7_PRO
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.PreviewScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainerWithTitle
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    result: List<Location>,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    onLocationClick: (Location) -> Unit,
) {

    ScreenContainerWithTitle(
        title = "Search",
        spacerHeight = 8.dp
    ) {
        Row(
            modifier = Modifier
        ) {
            SearchBar(
                modifier = Modifier.padding(0.dp),
                inputField = {
                    SearchBarDefaults.InputField(
                        query = query,
                        onQueryChange = { onQueryChange(it) },
                        onSearch = { onSearch(query) },
                        expanded = false,
                        onExpandedChange = {},
                        modifier = Modifier
                            .sizeIn(
                                minHeight = 25.dp
                            )
                    )
                },
                expanded = false,
                onExpandedChange = {},
                content = {}
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        if (result.isEmpty()) {
            Text(
                text = stringResource(R.string.result_list_is_empty),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            items(result) { location ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
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
                    Text(
                        text = "+",
                        color = Color(0xFF383838),
                        modifier = Modifier
                            .clickable(onClick = { onLocationClick(location) })
                            .background(
                                color = Color(0x9ED1C4E9),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .padding(vertical = 10.dp, horizontal = 15.dp)
                    )
                }
            }
        }
    }
}

@Preview(device = PIXEL_7_PRO)
@Composable
fun SearchScreenPreview() {
    PreviewScreenContainer {
        SearchScreen(
            result = listOf(
                Location(
                    id = 1,
                    longitude = 12.0,
                    latitude = 14.0,
                    shortName = "Nova Vodolaha",
                    fullName = "Nova vodolaha"
                )
            ),
            query = "Hello world",
            onQueryChange = {},
            onSearch = {},
            onLocationClick = {},
        )
    }
}