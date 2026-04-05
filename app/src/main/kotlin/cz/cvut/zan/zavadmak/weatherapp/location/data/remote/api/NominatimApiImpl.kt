package cz.cvut.zan.zavadmak.weatherapp.location.data.remote.api

import android.util.Log
import cz.cvut.zan.zavadmak.weatherapp.location.data.remote.model.LocationDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode

class NominatimApiImpl(
    private val client: HttpClient
) : GeoLocationApi {
    override suspend fun search(query: String): List<LocationDto> {
        return try {
            val res = client.get("https://nominatim.openstreetmap.org/search.php") {
                // https://nominatim.openstreetmap.org/search.php?q=nova+vodolaha&exclude_place_ids=178709946&format=jsonv2
                url {
                    parameters.append("city", query)
                    parameters.append("addressdetails", "1")
                    parameters.append("format", "jsonv2")
                }
            }

            if (res.status != HttpStatusCode.OK) {
                throw Exception("Bad request")
            }
            res.body()
        } catch (e: Exception) {
            Log.e("searchForLocation", e.message, e)
            return listOf()
        }
    }
}