package com.andromite.asteroidneo.network.models


import com.google.gson.annotations.SerializedName

data class OrbitalData(
    @SerializedName("aphelion_distance")
    var aphelionDistance: String? = null,
    @SerializedName("ascending_node_longitude")
    var ascendingNodeLongitude: String? = null,
    @SerializedName("data_arc_in_days")
    var dataArcInDays: Int? = null,
    @SerializedName("eccentricity")
    var eccentricity: String? = null,
    @SerializedName("epoch_osculation")
    var epochOsculation: String? = null,
    @SerializedName("equinox")
    var equinox: String? = null,
    @SerializedName("first_observation_date")
    var firstObservationDate: String? = null,
    @SerializedName("inclination")
    var inclination: String? = null,
    @SerializedName("jupiter_tisserand_invariant")
    var jupiterTisserandInvariant: String? = null,
    @SerializedName("last_observation_date")
    var lastObservationDate: String? = null,
    @SerializedName("mean_anomaly")
    var meanAnomaly: String? = null,
    @SerializedName("mean_motion")
    var meanMotion: String? = null,
    @SerializedName("minimum_orbit_intersection")
    var minimumOrbitIntersection: String? = null,
    @SerializedName("observations_used")
    var observationsUsed: Int? = null,
    @SerializedName("orbit_class")
    var orbitClass: OrbitClass? = null,
    @SerializedName("orbit_determination_date")
    var orbitDeterminationDate: String? = null,
    @SerializedName("orbit_id")
    var orbitId: String? = null,
    @SerializedName("orbit_uncertainty")
    var orbitUncertainty: String? = null,
    @SerializedName("orbital_period")
    var orbitalPeriod: String? = null,
    @SerializedName("perihelion_argument")
    var perihelionArgument: String? = null,
    @SerializedName("perihelion_distance")
    var perihelionDistance: String? = null,
    @SerializedName("perihelion_time")
    var perihelionTime: String? = null,
    @SerializedName("semi_major_axis")
    var semiMajorAxis: String? = null
)