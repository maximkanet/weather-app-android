package cz.cvut.zan.zavadmak.weatherapp.location.data.repository

import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api.NominatimApi
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.mapper.toDomainModel

class LocationsRepositoryImpl(
    private val nominatim: NominatimApi
) : LocationsRepository {
    override suspend fun getLocations(): List<Location> {
        return listOf()
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return nominatim.search(query).map {
            it.toDomainModel()
        }
    }

    override suspend fun addLocation(location: Location) {

    }

    override suspend fun removeLocation(locationId: Int) {

    }
}