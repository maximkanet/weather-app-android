package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class AddLocationUseCaseImpl(
    private val repository: LocationsRepository
) : AddLocationUseCase {
    override suspend fun execute(location: Location) {
        repository.addLocation(location)
    }
}