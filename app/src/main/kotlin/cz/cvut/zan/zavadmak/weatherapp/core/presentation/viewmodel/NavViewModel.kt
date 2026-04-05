package cz.cvut.zan.zavadmak.weatherapp.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class NavViewModel(
    initScreen: Any
) : ViewModel() {

    private val _currentScreen = MutableStateFlow<Any>(initScreen)
    val currentScreen: Any = _currentScreen.asStateFlow()

    fun changeScreen(screen: Any) {
        _currentScreen.update {
            screen
        }
    }

}