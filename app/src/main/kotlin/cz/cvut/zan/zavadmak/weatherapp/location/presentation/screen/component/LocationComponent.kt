package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography

@Composable
fun LocationComponent(
    onClick: () -> Unit,
    name: String,
    state: String,
    country: String,
) {
    val stateAndCountry by remember {
        mutableStateOf(if (state.isNotEmpty()) "$state, $country" else country)
    }

    Column(
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(vertical = 15.dp, horizontal = 32.dp)
            .clickable(onClick = onClick)
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            style = Typography.bodyLarge,
        )
        Text(
            text = stateAndCountry,
            style = Typography.bodySmall,
        )
    }
}