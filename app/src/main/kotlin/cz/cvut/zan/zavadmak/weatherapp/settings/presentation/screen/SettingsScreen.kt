package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.ScreenContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers.TopBarContainer
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography
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
    notificationsAllowed: Boolean,
    onNotificationsEnable: (Boolean) -> Unit,
    notifications: List<NotificationUiState>
) {
    ScreenContainer(
        topBar = { SettingsTopBar(onCloseClick = onCloseClick) }
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
            item {
                AllowNotifications(
                    notificationsAllowed = notificationsAllowed,
                    onNotificationToggle = { onNotificationsEnable(it) }
                )
            }

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

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun AllowNotifications(
    onNotificationToggle: (Boolean) -> Unit,
    notificationsAllowed: Boolean
) {
    val permissionState = rememberPermissionState(android.Manifest.permission.POST_NOTIFICATIONS)

    var waitForPermission by remember { mutableStateOf(false) }

    LaunchedEffect(permissionState.status) {
        if (permissionState.status.isGranted == false) {
            onNotificationToggle(false)
        } else {
            if (waitForPermission) {
                waitForPermission = false
                onNotificationToggle(true) // toggle to enabled
            }
        }
    }

    CheckableOption(
        text = stringResource(R.string.allow_all_notifications),
        description = null,
        checked = notificationsAllowed && permissionState.status.isGranted,
        onCheckedChange = {
            // When permissions are not granted
            if (!notificationsAllowed && permissionState.status.isGranted == false) {
                waitForPermission = true
                permissionState.launchPermissionRequest()
            } else {
                onNotificationToggle(it)
            }
        }
    )
}

@Composable
fun SettingsTopBar(
    onCloseClick: () -> Unit
) {
    TopBarContainer(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.settings),
            style = Typography.titleLarge,
            modifier = Modifier.weight(1f)
        )
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
