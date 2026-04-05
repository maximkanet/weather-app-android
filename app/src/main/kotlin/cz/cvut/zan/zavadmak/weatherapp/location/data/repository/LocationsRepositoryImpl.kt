package cz.cvut.zan.zavadmak.weatherapp.location.data.repository

import android.Manifest
import androidx.annotation.RequiresPermission
import com.google.android.gms.location.FusedLocationProviderClient
import cz.cvut.zan.zavadmak.weatherapp.location.data.local.source.LocationLocalDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source.LocationRemoteDataSource
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import kotlinx.coroutines.tasks.await

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

        val rawLocation = Location(
            id = 0, longitude = 0.0, latitude = 0.0, name = "", state = "", country = ""
        )

        val localLocation = localDataSource
            .getLocation(lastLocation.latitude, lastLocation.longitude)

        return localLocation ?: remoteDataSource.findLocation(
            latitude = lastLocation.latitude,
            longitude = lastLocation.longitude
        ) ?: rawLocation
    }

    override suspend fun getLocations(): List<Location> {
        return localDataSource.getLocations()
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

    override suspend fun addLocation(location: Location) {
        localDataSource.addLocation(location)
    }

    override suspend fun removeLocation(locationId: Long) {
        localDataSource.removeLocation(locationId)
    }
}