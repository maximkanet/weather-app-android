package cz.cvut.zan.zavadmak.weatherapp.location.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
enum class AddressType {
    @SerialName("city")
    CITY,
    @SerialName("town")
    TOWN,
    @SerialName("village")
    VILLAGE,
    @SerialName("suburb")
    SUBURB,
    @SerialName("neighbourhood")
    NEIGHBOURHOOD,
    @SerialName("borough")
    BOROUGH,
    @SerialName("hamlet")
    HAMLET,
    @SerialName("commercial")
    COMMERCIAL,
    @Transient
    ANY
}