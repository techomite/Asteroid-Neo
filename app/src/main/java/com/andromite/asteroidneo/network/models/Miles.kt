package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class Miles(
    @SerializedName("estimated_diameter_max")
    var estimatedDiameterMax: Double? = null,
    @SerializedName("estimated_diameter_min")
    var estimatedDiameterMin: Double? = null
)