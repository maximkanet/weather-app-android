package cz.cvut.zan.zavadmak.weatherapp.core.presentation.component

import androidx.annotation.DrawableRes
import cz.cvut.zan.zavadmak.weatherapp.R
import cz.cvut.zan.zavadmak.weatherapp.core.presentation.navigation.MainScreens

sealed class BottomNavBarButton(
    val screen: Any,
    val label: String,
    @DrawableRes
    val iconId: Int,
    val contentDescription: String,
) {

    data object Home : BottomNavBarButton(
        screen = MainScreens.Weather,
        label = "Home screen",
        iconId = R.drawable.clear,
        contentDescription = ""
    )

    data object Cities : BottomNavBarButton(
        screen = MainScreens.Locations,
        label = "Home screen",
        iconId = R.drawable.clear,
        contentDescription = ""
    )

//    data object Settings : BottomNavBarButton(
//        screen = MainScreens.Settings,
//        label = "Home screen",
//        iconId = R.drawable.clear,
//        contentDescription = ""
//    )

    companion object {

        fun asDefaultList(): List<BottomNavBarButton> {
            return listOf(
                Home,
                Cities,
//                Settings
            )
        }

    }
}