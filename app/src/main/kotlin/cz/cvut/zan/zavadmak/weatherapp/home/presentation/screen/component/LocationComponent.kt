package cz.cvut.zan.zavadmak.weatherapp.home.presentation.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                color = Color(0xFFF1F1F1),
                shape = RoundedCornerShape(32.dp)
            )
            .padding(vertical = 13.dp, horizontal = 20.dp)
            .clickable(
                onClick = onClick
            )
            .fillMaxWidth()
    ) {
        Text(
            text = name,
            fontSize = 14.sp
        )
        Text(
            text = stateAndCountry,
            fontSize = 13.sp,
            color = Color(0xFF939393)
        )
    }
}