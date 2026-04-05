package cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class SearchForLocationUseCaseImpl(
    private val repository: LocationsRepository
) : SearchForLocationUseCase {
    override suspend fun execute(locationName: String): List<Location> {
        return repository.searchLocation(locationName)
    }
}