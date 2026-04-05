package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLocationsUseCaseImpl(
    private val repository: LocationsRepository
) : GetLocationsUseCase {
    override suspend fun execute(): List<Location> {
        return repository.getLocations()
    }
}