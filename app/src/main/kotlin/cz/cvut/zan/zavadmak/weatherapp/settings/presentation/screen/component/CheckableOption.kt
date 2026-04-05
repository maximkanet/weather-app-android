package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun CheckableOption(
    modifier: Modifier = Modifier,
    text: String,
    description: String? = null,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    OptionWrapper(
        modifier = modifier,
        text = text,
        description = description
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}