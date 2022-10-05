package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class OrbitClass(
    @SerializedName("orbit_class_description")
    var orbitClassDescription: String? = null,
    @SerializedName("orbit_class_range")
    var orbitClassRange: String? = null,
    @SerializedName("orbit_class_type")
    var orbitClassType: String? = null
)