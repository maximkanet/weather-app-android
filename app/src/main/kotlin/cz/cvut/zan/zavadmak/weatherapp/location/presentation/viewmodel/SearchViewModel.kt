package cz.cvut.zan.zavadmak.weatherapp.location.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase.SearchLocationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchLocationUseCase: SearchLocationUseCase
) : ViewModel() {

    private val _locations = MutableStateFlow<List<Location>>(listOf())
    val locations = _locations.asStateFlow()

    private fun applyLocations(locations: List<Location>) {
        _locations.update { locations }
    }

    private val _query = MutableStateFlow<String>("")
    val query = _query.asStateFlow()

    private fun applyQuery(query: String) {
        _query.update { query }
    }

    fun searchLocations(query: String) {
        applyQuery(query)

        viewModelScope.launch {
            applyLocations(searchLocationUseCase.execute(query))
        }
    }

}