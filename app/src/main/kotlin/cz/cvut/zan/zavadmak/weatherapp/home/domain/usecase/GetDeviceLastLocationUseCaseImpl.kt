package cz.cvut.zan.zavadmak.weatherapp.home.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetDeviceLastLocationUseCaseImpl(
    private val repository: LocationsRepository
) : GetDeviceLastLocationUseCase {

    override suspend fun execute(): Location {
        return repository.getDeviceLastLocation()
    }

}