package cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ScreenContainer(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Scaffold(
        topBar = topBar
    ) { innerPadding ->
        Column(
            modifier = modifier
                .padding(ScreenContainerPaddings)
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content()
        }
    }
}