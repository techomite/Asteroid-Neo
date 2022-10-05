package com.andromite.asteroidneo.network.models

data class DateNeoDetails(
    val date: String,
    val asteroidDetailList: MutableList<AsteroidDetails>? = null
)
