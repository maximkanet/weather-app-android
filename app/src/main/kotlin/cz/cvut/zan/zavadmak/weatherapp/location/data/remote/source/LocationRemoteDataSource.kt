package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

interface LocationRemoteDataSource {

    suspend fun findLocation(query: String): List<Location>

}