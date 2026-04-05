package cz.cvut.zan.zavadmak.weatherapp.location.data.repository

import cz.cvut.zan.zavadmak.weatherapp.location.data.local.source.LocationLocalDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source.LocationRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

class LocationsRepositoryImpl(
    private val localDataSource: LocationLocalDataSource,
    private val remoteDataSource: LocationRemoteDataSource,
) : LocationsRepository {

    override suspend fun getLocations(): List<Location> {
        return localDataSource.getLocations()
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return remoteDataSource.findLocation(query)
    }

    override suspend fun addLocation(location: Location) {
        localDataSource.addLocation(location)
    }

    override suspend fun removeLocation(locationId: Long) {
        localDataSource.removeLocation(locationId)
    }
}