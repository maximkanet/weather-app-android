package cz.cvut.zan.zavadmak.weatherapp.location.data.repository

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface LocationsRepository {

    suspend fun getLocations(): List<Location>

    suspend fun searchLocation(query: String): List<Location>

    suspend fun addLocation(location: Location)

    suspend fun removeLocation(locationId: Int)

}