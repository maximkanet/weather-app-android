package cz.cvut.zan.zavadmak.weatherapp.location.data.local.source

import cz.cvut.zan.zavadmak.weatherapp.location.data.local.dao.LocationDao
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.mapper.toDataModel
import cz.cvut.zan.zavadmak.weatherapp.location.mapper.toDomainModel

class LocationLocalDataSourceImpl(
    private val locationDao: LocationDao
) : LocationLocalDataSource {

    override suspend fun getLocation(
        longitude: Double,
        latitude: Double
    ): Location? {
        return null
    }

    override suspend fun getLocations(): List<Location> {
        return locationDao.getLocations().map {
            it.toDomainModel()
        }
    }

    override suspend fun getLocation(id: Long): Location? {
        return locationDao.getLocationById(id)?.toDomainModel()
    }

    override suspend fun getLastLocations(count: Int): List<Location> {
        return locationDao.getLastLocations(count).map {
            it.toDomainModel()
        }
    }

    override suspend fun addLocation(location: Location): Location {
        val inserted = locationDao.insertLocation(location.toDataModel())
        return locationDao.getLocationById(inserted)!!.toDomainModel()
    }

    override suspend fun removeLocation(locationId: Long) {
        val location = locationDao.getLocationById(locationId) ?: return
        locationDao.deleteLocation(location)
    }

}