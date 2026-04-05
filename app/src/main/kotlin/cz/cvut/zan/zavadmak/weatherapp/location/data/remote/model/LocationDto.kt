package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model

import cz.cvut.zan.zavadmak.weatherapp.location.domain.model.AddressType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationDto(
    @SerialName("place_id")
    val placeId: Long,
    val lat: Double,
    val lon: Double,
    val name: String,
    @SerialName("display_name")
    val displayName: String,
    val addresstype: AddressType = AddressType.ANY,
    val address: AddressDto,
)
