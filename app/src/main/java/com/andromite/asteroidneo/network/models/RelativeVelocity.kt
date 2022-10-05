package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class RelativeVelocity(
    @SerializedName("kilometers_per_hour")
    var kilometersPerHour: String? = null,
    @SerializedName("kilometers_per_second")
    var kilometersPerSecond: String? = null,
    @SerializedName("miles_per_hour")
    var milesPerHour: String? = null
)