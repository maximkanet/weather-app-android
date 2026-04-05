package cz.cvut.zan.zavadmak.weatherapp.location.domain.model

import kotlinx.serialization.Serializable

@Serializable
enum class LocationType {
    LOCAL,
    GLOBAL
}