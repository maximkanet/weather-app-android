package cz.cvut.zan.zavadmak.weatherapp.core.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Table(
    columns: @Composable RowScope.() -> Unit,
    itemsGap: Dp = 10.dp,
    headBottomMargin: Dp = 10.dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column {
        TableRow {
            columns()
        }
        Spacer(modifier = Modifier.height(headBottomMargin))
        Column(
            verticalArrangement = Arrangement.spacedBy(itemsGap)
        ) {
            content()
        }
    }
}

@Composable
fun RowScope.TableCell(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    weight: Float = 1f,
    content: @Composable () -> Unit
) {
    Row(
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
        modifier = modifier.weight(weight)
    ) {
        content()
    }
}

@Composable
fun ColumnScope.TableRow(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        content()
    }
}

@Composable
fun <T> ColumnScope.TableRows(
    items: List<T>,
    content: @Composable (T) -> Unit
) {
    items.map { item ->
        content(item)
    }
}