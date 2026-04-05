package cz.cvut.zan.zavadmak.weatherapp.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetDeviceLastLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.GetLastLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase.RemoveSelectedLocationsUseCase
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.LocationRequestUiState
import cz.cvut.zan.zavadmak.weatherapp.home.presentation.model.ScreenMode
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase.MarkLocationsAsUsedUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

const val LAST_LOCATIONS_COUNT = 5

class HomeViewModel(
    private val getLastLocationsUseCase: GetLastLocationsUseCase,
    private val getDeviceLastLocationUseCase: GetDeviceLastLocationUseCase,
    private val markLocationsAsUsedUseCase: MarkLocationsAsUsedUseCase,
    private val removeSelectedLocationsUseCase: RemoveSelectedLocationsUseCase
) : ViewModel() {

    private val _locationRequest = MutableStateFlow<LocationRequestUiState>(
        LocationRequestUiState.Idle()
    )
    val locationRequest = _locationRequest.asStateFlow()

    private val _lastLocation = MutableStateFlow<Location?>(null)
    val lastLocation = _lastLocation.asStateFlow()

    fun setRequestAsIdle() {
        _locationRequest.update {
            LocationRequestUiState.Idle()
        }
    }

    fun setRequestAsGetting() {
        _locationRequest.update {
            LocationRequestUiState.Getting()
        }
    }

    fun setRequestAsSuccess() {
        _locationRequest.update {
            LocationRequestUiState.Success()
        }
    }

    fun getDeviceLastLocation() {
        viewModelScope.launch {
            setRequestAsGetting()
            _lastLocation.update { getDeviceLastLocationUseCase.execute() }
            setRequestAsSuccess()
        }
    }

    private val _lastLocations = MutableStateFlow<List<Location>>(listOf())
    val lastLocations = _lastLocations.asStateFlow()

    fun fetchLastLocations() {
        viewModelScope.launch {
            _lastLocations.update { getLastLocationsUseCase.execute(LAST_LOCATIONS_COUNT) }
        }
    }

    fun markLocationAsUsed(id: Long) {
        viewModelScope.launch {
            markLocationsAsUsedUseCase.execute(id)
        }
    }

    private val _mode = MutableStateFlow<ScreenMode>(ScreenMode.IDLE)
    val mode = _mode.asStateFlow()

    private val _selectedItems = MutableStateFlow<Set<Long>>(setOf())
    val selectedItems = _selectedItems.asStateFlow()

    private val _initiator = MutableStateFlow<Long>(0)
    val initiator = _initiator.asStateFlow()

    fun switchModeToSelection(initiatorId: Long) {
        _selectedItems.update { setOf(initiatorId) }
        _initiator.update { initiatorId }
        _mode.update { ScreenMode.SELECTION }
    }

    fun switchModeToIdle() {
        _mode.update { ScreenMode.IDLE }
    }

    fun processSelection(id: Long, checked: Boolean) {
        if (checked) {
            _selectedItems.update { selectedItems.value + id }
        } else {
            _selectedItems.update { selectedItems.value - id }
            if (selectedItems.value.isEmpty()) {
                switchModeToIdle()
            }
        }
    }

    fun removeSelectedLocations() {
        viewModelScope.launch {
            removeSelectedLocationsUseCase.execute(selectedItems.value.toList())
            _lastLocations.update { getLastLocationsUseCase.execute(LAST_LOCATIONS_COUNT) }
        }
    }

    init {
        fetchLastLocations()
    }

}