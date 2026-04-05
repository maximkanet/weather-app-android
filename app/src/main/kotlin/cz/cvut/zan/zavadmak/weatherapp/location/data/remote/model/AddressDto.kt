package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    val state: String = "",
    val country: String = ""
)
