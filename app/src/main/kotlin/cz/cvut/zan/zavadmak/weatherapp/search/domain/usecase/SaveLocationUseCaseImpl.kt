package cz.cvut.zan.zavadmak.weatherapp.search.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class SaveLocationUseCaseImpl(
    private val repository: LocationsRepository
) : SaveLocationUseCase {
    override suspend fun execute(location: Location): Location {
        return repository.saveLocation(location)
    }
}