package cz.cvut.zan.zavadmak.weatherapp.core.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ButtonWithIcon(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: Int? = null,
) {
    Row(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Remove button icon",
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
            )
        )
    }
}