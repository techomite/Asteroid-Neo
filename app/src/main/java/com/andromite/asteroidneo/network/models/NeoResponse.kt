package com.andromite.asteroidneo.network.models


import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class NeoResponse(
    @SerializedName("element_count")
    var elementCount: Int? = 0,
    @SerializedName("links")
    var links: Links? = Links(),
    @SerializedName("near_earth_objects")
    var nearEarthObjects: JsonObject? = null
)