package cz.cvut.zan.zavadmak.weatherapp.location.data.local.source

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface LocationLocalDataSource {

    suspend fun getLocations(): List<Location>

    suspend fun addLocation(location: Location)

    suspend fun removeLocation(locationId: Long)

}