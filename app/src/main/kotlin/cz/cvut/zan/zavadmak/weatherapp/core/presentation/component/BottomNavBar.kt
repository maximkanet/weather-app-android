package cz.cvut.zan.zavadmak.weatherapp.core.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Purple80
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.theme.Transparent

@Composable
fun BottomNavBar(
    currentScreen: Any,
    buttons: List<BottomNavBarButton> = BottomNavBarButton.asDefaultList(),
    onButtonClick: (Any) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        buttons.forEach { button ->
            BottomNavBarButton(
                isActive = button.screen == currentScreen,
                button = button,
                onClick = {
                    onButtonClick(button.screen)
                }
            )
        }
    }
}

@Composable
private fun BottomNavBarButton(
    isActive: Boolean,
    button: BottomNavBarButton,
    onClick: () -> Unit
) {
    Box {
        Icon(
            painter = painterResource(button.iconId),
            contentDescription = button.contentDescription,
            modifier = Modifier
                .background(
                    color = if(isActive) Purple80 else Transparent
                )
                .clickable(
                    onClick = onClick
                )
                .size(32.dp)
        )
    }
}