package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class MissDistance(
    @SerializedName("astronomical")
    var astronomical: String? = null,
    @SerializedName("kilometers")
    var kilometers: String? = null,
    @SerializedName("lunar")
    var lunar: String? = null,
    @SerializedName("miles")
    var miles: String? = null
)