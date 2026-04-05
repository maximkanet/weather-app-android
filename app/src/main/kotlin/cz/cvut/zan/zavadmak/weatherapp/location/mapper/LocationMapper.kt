package cz.cvut.zan.zavadmak.weatherapp.location.mapper

import cz.cvut.zan.zavadmak.weatherapp.location.data.local.entity.LocationEntity
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model.LocationDto
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.AddressType
import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.Location
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

fun LocationDto.toDomainModel(): Location {
    return Location(
        id = this.placeId,
        longitude = this.lon,
        latitude = this.lat,
        name = when (this.addresstype) {
            AddressType.CITY -> this.address.city
            AddressType.TOWN -> this.address.town
            AddressType.VILLAGE -> this.address.village
            AddressType.SUBURB -> this.address.suburb
            AddressType.NEIGHBOURHOOD -> this.address.neighbourhood
            AddressType.BOROUGH -> this.address.borough
            AddressType.HAMLET -> this.address.hamlet
            AddressType.COMMERCIAL -> this.address.commercial
            AddressType.ANY -> "any"
        },
        state = this.address.state,
        country = this.address.country,
        lastUse = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    )
}

fun LocationEntity.toDomainModel(): Location {
    return Location(
        id = this.placeId,
        longitude = this.longitude,
        latitude = this.latitude,
        name = this.name,
        state = this.state,
        country = this.country,
        lastUse = Instant.fromEpochMilliseconds(this.lastUse)
            .toLocalDateTime(TimeZone.currentSystemDefault())
    )
}

fun Location.toDataModel(): LocationEntity {
    return LocationEntity(
        placeId = this.id,
        longitude = this.longitude,
        latitude = this.latitude,
        name = this.name,
        state = this.state,
        country = this.country,
        lastUse = this.lastUse.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    )
}