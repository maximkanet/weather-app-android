package cz.cvut.zan.zavadmak.weatherapp.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.search.domain.model.RequestState
import cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase.SaveLocationUseCase
import cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase.SearchForLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchForLocationUseCase: SearchForLocationUseCase,
    private val saveLocationUseCase: SaveLocationUseCase,
) : ViewModel() {

    private val _result = MutableStateFlow<List<Location>?>(null)
    val result = _result.asStateFlow()

    private fun applyLocations(locations: List<Location>) {
        _result.update { locations }
    }

    fun search(query: String) {
        viewModelScope.launch {
            applyLocations(searchForLocationUseCase.execute(query))
        }
    }

    private val _savedLocation = MutableStateFlow<Location?>(null)
    val savedLocation = _savedLocation.asStateFlow()

    private val _requestState = MutableStateFlow<RequestState>(RequestState.IDLE)
    val requestState = _requestState.asStateFlow()

    fun setRequestAsIdle() {
        _requestState.update { RequestState.IDLE }
    }

    fun setRequestAsSuccess() {
        _requestState.update { RequestState.SUCCESS }
    }

    fun setRequestAsGetting() {
        _requestState.update { RequestState.GETTING }
    }

    fun saveLocation(location: Location) {
        viewModelScope.launch {
            setRequestAsGetting()
            _savedLocation.update { saveLocationUseCase.execute(location) }
            setRequestAsSuccess()
        }
    }

}