package cz.cvut.zan.zavadmak.weatherapp.location.data.local.source

import cz.cvut.zan.zavadmak.weatherapp.location.data.local.dao.LocationDao
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.mapper.toDataModel
import cz.cvut.zan.zavadmak.weatherapp.location.mapper.toDomainModel

class LocationLocalDataSourceImpl(
    private val locationDao: LocationDao
) : LocationLocalDataSource {

    override suspend fun getLocations(): List<Location> {
        return locationDao.getLocations().map {
            it.toDomainModel()
        }
    }

    override suspend fun addLocation(location: Location) {
        locationDao.insertLocation(location.toDataModel())
    }

    override suspend fun removeLocation(locationId: Long) {
        val location = locationDao.getLocationById(locationId) ?: return
        locationDao.deleteLocation(location)
    }

}