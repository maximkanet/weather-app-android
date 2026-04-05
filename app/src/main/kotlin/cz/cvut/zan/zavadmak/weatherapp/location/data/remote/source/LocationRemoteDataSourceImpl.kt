package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.source

import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api.GeoLocationApi
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import cz.cvut.zan.zavadmak.weatherapp.location.mapper.toDomainModel

class LocationRemoteDataSourceImpl(
    private val api: GeoLocationApi
) : LocationRemoteDataSource {
    override suspend fun findLocation(query: String): List<Location> {
        return api.search(query).map { it.toDomainModel() }
    }
}