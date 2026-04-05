package cz.cvut.zan.zavadmak.weatherapp.location.data.local.source

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface LocationLocalDataSource {

    suspend fun getLocation(longitude: Double, latitude: Double): Location?

    suspend fun getLocation(id: Long): Location?

    suspend fun getLocations(): List<Location>

    suspend fun getLastLocations(count: Int): List<Location>

    suspend fun addLocation(location: Location): Location

    suspend fun removeLocation(locationId: Long)

}