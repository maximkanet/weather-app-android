package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component

import android.Manifest
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.LocationRequestUiState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun GetDeviceLocationButton(
    onGetLocationClick: () -> Unit,
    requestState: LocationRequestUiState
) {
    val permissionsState = rememberMultiplePermissionsState(listOf(
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
    ))

    LaunchedEffect(Unit) {
        permissionsState.launchMultiplePermissionRequest()
    }

    Button(
        enabled = permissionsState.allPermissionsGranted,
        onClick = {
            onGetLocationClick()
        }
    ) {
        Icon(
            painter = painterResource(R.drawable.outline_near_me_24),
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = stringResource(requestState.message)
        )
    }
}