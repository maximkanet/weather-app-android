package cz.cvut.zan.zavadmak.weatherapp.location.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLocationUseCaseImpl(
    private val repository: LocationsRepository,
) : GetLocationUseCase {
    override suspend fun execute(longitude: Double, latitude: Double): Location? {
        return repository.reverseLocation(longitude = longitude, latitude = latitude)
    }
}