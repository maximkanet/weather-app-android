package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class AddressDto(
    val city: String = "",
    val town: String = "",
    val village: String = "",
    val suburb: String = "",
    val neighbourhood: String = "",
    val borough: String = "",
    val hamlet: String = "",
    val commercial: String = "",
    val state: String = "",
    val country: String = ""
)
