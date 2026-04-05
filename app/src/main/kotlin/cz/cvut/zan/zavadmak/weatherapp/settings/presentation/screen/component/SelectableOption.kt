package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun <T> SelectableOption(
    modifier: Modifier = Modifier,
    text: String,
    description: String? = null,
    items: List<T>,
    toStringMapper: (T) -> String,
    onItemSelect: (T) -> Unit,
    comparator: (T, T) -> Boolean,
    current: T,
) {

    OptionWrapper(
        modifier = modifier,
        text = text,
        description = description
    ) {

        Box(
            contentAlignment = Alignment.BottomEnd
        ) {
            ItemPicker(
                current = current,
                items = items,
                comparator = comparator,
                toStringMapper = toStringMapper,
                onItemSelect = onItemSelect,
            )
        }
    }
}