package cz.cvut.zan.zavadmak.weatherapp.core.presentation.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ButtonWithIcon(
    text: String,
    onClick: () -> Unit,
    icon: Int? = null,
    borderColor: Color = Color(0xFF000000),
    cornerRadius: Dp = 20.dp,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .clickable(
                onClick = onClick
            )
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(cornerRadius)
            )
            .padding(vertical = 10.dp, horizontal = 20.dp)
    ) {
        if (icon != null) {
            Icon(
                painter = painterResource(icon),
                contentDescription = "Remove button icon"
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