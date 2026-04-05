package cz.cvut.zan.zavadmak.weatherapp.location.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Typography

@Composable
fun LocationSelectableComponent(
    onCheckedChange: (Boolean) -> Unit,
    selected: Boolean,
    name: String,
    state: String,
    country: String
) {
    val stateAndCountry by remember {
        mutableStateOf(if (state.isNotEmpty()) "$state, $country" else country)
    }

    val selectedColor = MaterialTheme.colorScheme.tertiaryContainer
    val normalColor = MaterialTheme.colorScheme.surfaceContainer

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(
                color = if (selected) selectedColor else normalColor,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(vertical = 15.dp, horizontal = 32.dp)
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = name,
                style = Typography.bodyLarge,
            )
            Text(
                text = stateAndCountry,
                style = Typography.bodySmall,
            )
        }

        Box {
            Checkbox(
                checked = selected,
                onCheckedChange = onCheckedChange,
                modifier = Modifier.heightIn(max = 20.dp)
            )
        }
    }
}