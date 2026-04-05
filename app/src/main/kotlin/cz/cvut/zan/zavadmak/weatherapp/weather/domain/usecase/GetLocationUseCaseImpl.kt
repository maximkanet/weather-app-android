package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class GetLocationUseCaseImpl(
    private val repository: LocationsRepository,
) : GetLocationUseCase {
    override suspend fun execute(id: Long): Location? {
        return repository.getLocationById(id)
    }
}