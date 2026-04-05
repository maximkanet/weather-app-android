package cz.cvut.zan.zavadmak.weatherapp.weather.domain.usecase

import cz.cvut.zan.zavadmak.weatherapp.location.data.repository.LocationsRepository
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.weather.domain.utils.getCurrentDateTime

class MarkLocationsAsUsedUseCaseImpl(
    private val repository: LocationsRepository
) : MarkLocationsAsUsedUseCase {
    override suspend fun execute(id: Long) {
        val location = repository.getLocationById(id)
        if (location == null) return

        repository.saveLocation(
            Location(
                id = location.id,
                longitude = location.longitude,
                latitude = location.latitude,
                name = location.name,
                state = location.state,
                country = location.country,
                lastUse = getCurrentDateTime()
            )
        )
    }
}