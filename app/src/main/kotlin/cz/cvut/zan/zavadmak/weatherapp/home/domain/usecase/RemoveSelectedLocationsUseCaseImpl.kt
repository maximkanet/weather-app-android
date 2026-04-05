package cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository

class RemoveSelectedLocationsUseCaseImpl(
    private val repository: LocationsRepository
) : RemoveSelectedLocationsUseCase {
    override suspend fun execute(items: List<Long>) {
        for (item in items) {
            repository.removeLocation(item)
        }
    }
}