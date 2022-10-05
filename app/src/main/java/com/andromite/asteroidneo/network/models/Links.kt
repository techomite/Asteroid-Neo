package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("next")
    var next: String? = null,
    @SerializedName("previous")
    var previous: String? = null,
    @SerializedName("self")
    var self: String? = null
)