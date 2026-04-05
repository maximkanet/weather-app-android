package cz.cvut.zan.zavadmak.weatherapp.settings.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationState
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.NotificationType
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.model.WeatherUnit
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetNotificationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.GetWeatherUnitsUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.SetNotificationStateUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.domain.usecase.SetWeatherUnitUseCase
import cz.cvut.zan.zavadmak.weatherapp.settings.mapper.toUiState
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model.NotificationUiState
import cz.cvut.zan.zavadmak.weatherapp.settings.presentation.model.UnitUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val setWeatherUnitUseCase: SetWeatherUnitUseCase,
    private val getWeatherUnitsUseCase: GetWeatherUnitsUseCase,
    private val getNotificationsUseCase: GetNotificationsUseCase,
    private val setNotificationStateUseCase: SetNotificationStateUseCase,
) : ViewModel() {

    private val _units = MutableStateFlow<List<UnitUiState>>(listOf())
    val units = _units.asStateFlow()

    private fun applyUnits(units: List<WeatherUnit>) {
        _units.update {
            units.map { it.toUiState() }
        }
    }

    fun changeUnit(unit: WeatherUnit) {
        viewModelScope.launch {
            setWeatherUnitUseCase.execute(unit)
            applyUnits(getWeatherUnitsUseCase.execute())
        }
    }

    fun fetchUnits() {
        viewModelScope.launch {
            applyUnits(getWeatherUnitsUseCase.execute())
        }
    }

    private val _notifications = MutableStateFlow<List<NotificationUiState>>(listOf())
    val notifications = _notifications.asStateFlow()

    fun changeNotificationState(type: NotificationType, checked: Boolean) {
        viewModelScope.launch {
            setNotificationStateUseCase.execute(type, checked)
            applyNotifications(getNotificationsUseCase.execute())
        }
    }

    fun applyNotifications(notifications: List<NotificationState>) {
        _notifications.update {
            notifications.map { it.toUiState() }
        }
    }

    fun fetchNotificationsState() {
        viewModelScope.launch {
            applyNotifications(getNotificationsUseCase.execute())
        }
    }

    init {
        fetchUnits()
        fetchNotificationsState()
    }
}