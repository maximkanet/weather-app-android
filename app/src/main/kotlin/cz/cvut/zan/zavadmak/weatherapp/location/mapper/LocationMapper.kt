package cz.cvut.zan.zavadmak.weatherapp.location.mapper

import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model.LocationDto
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location

fun LocationDto.toDomainModel() : Location {
    return Location(
        id = this.placeId,
        longitude = this.lon,
        latitude = this.lat,
        shortName = this.name,
        fullName = this.displayName,
        state = this.address.state,
        country = this.address.country,
    )
}