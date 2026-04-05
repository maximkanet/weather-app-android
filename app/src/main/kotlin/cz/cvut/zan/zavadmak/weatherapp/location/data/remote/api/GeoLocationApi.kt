package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api

import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model.LocationDto

interface GeoLocationApi {

    suspend fun search(query: String): List<LocationDto>

}