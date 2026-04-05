package cz.cvut.zan.zavadmak.weatherapp.location.mapper

import cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity.LocationEntity
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model.LocationDto
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

fun LocationDto.toDomainModel() : Location {
    return Location(
        id = this.placeId,
        longitude = this.lon,
        latitude = this.lat,
        name = this.name,
        state = this.address.state,
        country = this.address.country,
    )
}

fun LocationEntity.toDomainModel(): Location {
    return Location(
        id = this.placeId,
        longitude = this.longitude,
        latitude = this.latitude,
        name = this.name,
        state = this.state,
        country = this.country
    )
}

fun Location.toDataModel(): LocationEntity {
    return LocationEntity(
        placeId = this.id,
        longitude = this.longitude,
        latitude = this.latitude,
        name = this.name,
        state = this.state,
        country = this.country
    )
}