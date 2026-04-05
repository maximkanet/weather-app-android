package cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLastLocationsUseCaseImpl(
    private val repository: LocationsRepository
) : GetLastLocationsUseCase {
    override suspend fun execute(count: Int): List<Location> {
        return repository.getLastLocations(count)
    }
}