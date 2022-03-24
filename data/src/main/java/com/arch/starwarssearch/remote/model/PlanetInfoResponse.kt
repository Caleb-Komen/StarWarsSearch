package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class PlanetInfoResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("population") val population: String,
    @field:SerializedName("gravity") val gravity: String
)

data class PlanetResponse(@field:SerializedName("homeworld") val homeWorld: String)
