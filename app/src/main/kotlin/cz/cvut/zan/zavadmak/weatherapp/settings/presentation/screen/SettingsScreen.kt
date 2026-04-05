package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model.NotificationUiState
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model.UnitUiState
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component.CheckableOption
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component.SectionComponent
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component.SelectableOption

@Composable
fun SettingsScreen(
    onCloseClick: () -> Unit,
    units: List<UnitUiState>,
    onUnitChange: (WeatherUnit) -> Unit,
    onNotificationToggle: (NotificationType, Boolean) -> Unit,
    notifications: List<NotificationUiState>
) {
    Scaffold(
        topBar = { SettingsTopBar(onCloseClick) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .padding(paddingValues)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            SectionComponent(name = "Units") {
                items(items = units, key = { it.current.type }) {
                    SelectableOption(
                        text = stringResource(it.optionName),
                        description = if (it.optionDescription != null) stringResource(it.optionDescription) else null,
                        current = it.current,
                        items = it.all,
                        onItemSelect = onUnitChange,
                        toStringMapper = { it.description },
                        comparator = { a, b -> a.name == b.name }
                    )
                }
            }
            HorizontalDivider(modifier = Modifier.padding(vertical = 20.dp))
            SectionComponent("Notifications") {
                items(items = notifications) { n ->
                    CheckableOption(
                        text = stringResource(n.optionName),
                        description = if (n.optionDescription != null) stringResource(n.optionDescription) else null,
                        checked = n.checked,
                        onCheckedChange = { onNotificationToggle(n.type, it) }
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsTopBar(
    onCloseClick: () -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 13.dp, vertical = 10.dp)
            .fillMaxWidth()
    ) {
        Row {
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = stringResource(R.string.settings),
                fontSize = 20.sp
            )
        }
        IconButton(
            onClick = onCloseClick
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_clear_24),
                contentDescription = "Close button"
            )
        }
    }
}

//@Preview(device = PIXEL_7_PRO)
//@Composable
//fun SettingsScreenPreview() {
//    SettingsScreen(
//        onCloseClick = {},
//        units = listOf(),
//        onUnitChange = {},
//        onNotificationToggle = {n, a -> },
//        notifications = listOf(),
//    )
//}
