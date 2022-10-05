package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class EstimatedDiameter(
    @SerializedName("feet")
    var feet: Feet? = null,
    @SerializedName("kilometers")
    var kilometers: Kilometers? = null,
    @SerializedName("meters")
    var meters: Meters? = null,
    @SerializedName("miles")
    var miles: Miles? = null
)