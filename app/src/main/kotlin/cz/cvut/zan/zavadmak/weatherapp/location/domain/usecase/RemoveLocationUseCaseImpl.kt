package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository

class RemoveLocationUseCaseImpl(
    private val repository: LocationsRepository
) : RemoveLocationUseCase {
    override suspend fun execute(locationId: Long) {
        repository.removeLocation(locationId)
    }
}