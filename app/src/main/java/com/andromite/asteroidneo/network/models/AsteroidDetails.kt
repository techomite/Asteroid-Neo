package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class AsteroidDetails(
    @SerializedName("absolute_magnitude_h")
    var absoluteMagnitudeH: Double? = 0.0,
    @SerializedName("close_approach_data")
    var closeApproachData: List<CloseApproachData>? = listOf(),
    @SerializedName("designation")
    var designation: String? = "",
    @SerializedName("estimated_diameter")
    var estimatedDiameter: EstimatedDiameter? = EstimatedDiameter(),
    @SerializedName("id")
    var id: String? = "",
    @SerializedName("is_potentially_hazardous_asteroid")
    var isPotentiallyHazardousAsteroid: Boolean? = false,
    @SerializedName("is_sentry_object")
    var isSentryObject: Boolean? = false,
    @SerializedName("links")
    var links: Links? = Links(),
    @SerializedName("name")
    var name: String? = "",
    @SerializedName("nasa_jpl_url")
    var nasaJplUrl: String? = "",
    @SerializedName("neo_reference_id")
    var neoReferenceId: String? = "",
    @SerializedName("orbital_data")
    var orbitalData: OrbitalData? = OrbitalData()
)