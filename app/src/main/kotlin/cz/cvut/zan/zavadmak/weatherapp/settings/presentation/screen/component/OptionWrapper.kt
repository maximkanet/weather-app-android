package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionWrapper(
    modifier: Modifier = Modifier,
    text: String,
    description: String? = null,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .padding(end = 10.dp)
                .weight(1f)
        ) {
            Text(
                text = text,
                fontSize = 16.sp,
            )
            if (description != null) {
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color(0xFF979797)
                )
            }
        }

        content()
    }
}