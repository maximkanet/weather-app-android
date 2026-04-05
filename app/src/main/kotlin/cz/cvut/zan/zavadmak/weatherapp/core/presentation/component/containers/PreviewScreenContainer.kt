package cz.cvut.zan.zavadmak.weatherapp.core.presentation.component.containers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun PreviewScreenContainer(
    content: @Composable () -> Unit,
) {
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxSize()
        ) {
            content()
        }
    }
}