package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api

import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model.LocationDto

interface NominatimApi {

    suspend fun search(query: String): List<LocationDto>

}