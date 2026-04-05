package cz.cvut.zan.zavadmak.weatherapp.weather.presentation.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.TopBarContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component.ItemPicker

@Composable
fun ForecastTopBar(
    onBackClick: () -> Unit,
    forecastRange: Int,
    onDaysChange: (Int) -> Unit,
    location: Location?
) {
    TopBarContainer(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = "Back"
                )
            }
            Text(
                text = location?.name ?: "",
                style = Typography.titleSmall,
                modifier = Modifier.weight(1f)
            )
        }
        Box {
            ItemPicker(
                current = forecastRange,
                items = listOf(5, 7, 10, 14),
                comparator = { i1, i2 -> i1 == i2 },
                toStringMapper = { i -> "$i days" },
                onItemSelect = onDaysChange
            )
        }
    }
}