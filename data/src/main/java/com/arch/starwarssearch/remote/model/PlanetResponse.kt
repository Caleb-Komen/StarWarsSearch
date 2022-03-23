package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class PlanetResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("population") val population: String,
    @field:SerializedName("gravity") val gravity: String
)
