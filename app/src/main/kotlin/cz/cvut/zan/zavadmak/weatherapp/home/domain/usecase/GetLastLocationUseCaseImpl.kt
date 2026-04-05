package cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLastLocationUseCaseImpl(
    private val repository: LocationsRepository
) : GetLastLocationUseCase {

    override suspend fun execute(): Location {
        return repository.getDeviceLastLocation()
    }

}