package cz.cvut.zan.zavadmak.weatherapp.location.data.repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.source.LocationLocalDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source.LocationRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import kotlinx.coroutines.tasks.await
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class LocationsRepositoryImpl(
    private val localDataSource: LocationLocalDataSource,
    private val remoteDataSource: LocationRemoteDataSource,
    private val fusedLocationClient: FusedLocationProviderClient
) : LocationsRepository {

    @RequiresPermission(
        allOf = [
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ]
    )
    override suspend fun getDeviceLastLocation(): Location {
        val lastLocation = fusedLocationClient.lastLocation.await()

        val localLocation = localDataSource.getLocation(
            longitude = lastLocation.longitude,
            latitude = lastLocation.latitude
        )

        val remoteLocation = remoteDataSource.findLocation(
            longitude = lastLocation.longitude,
            latitude = lastLocation.latitude
        )

        val rawLocation = Location(
            id = 0,
            latitude = lastLocation.latitude,
            longitude = lastLocation.longitude,
            name = "${lastLocation.latitude}, ${lastLocation.longitude}",
            state = "---",
            country = "---",
            lastUse = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()),
        )

        return localLocation
            ?: if (remoteLocation != null)
                localDataSource.addLocation(remoteLocation)
            else
                localDataSource.addLocation(rawLocation)
    }

    override suspend fun getLocations(): List<Location> {
        return localDataSource.getLocations()
    }

    override suspend fun getLastLocations(count: Int): List<Location> {
        return localDataSource.getLastLocations(count)
    }

    override suspend fun getLocationById(id: Long): Location? {
        return localDataSource.getLocation(id)
    }

    override suspend fun searchLocation(query: String): List<Location> {
        return remoteDataSource.findLocation(query)
    }

    override suspend fun reverseLocation(
        longitude: Double,
        latitude: Double
    ): Location? {
        var location = localDataSource.getLocation(longitude = longitude, latitude = latitude)

        if (location == null) {
            location = remoteDataSource.findLocation(longitude = longitude, latitude = latitude)

            if (location != null) {
                localDataSource.addLocation(location)
            }
        }

        return location
    }

    override suspend fun saveLocation(location: Location): Location {
        return localDataSource.addLocation(location)
    }

    override suspend fun removeLocation(locationId: Long) {
        localDataSource.removeLocation(locationId)
    }
}