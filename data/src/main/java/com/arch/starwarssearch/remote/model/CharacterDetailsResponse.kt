package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterDetailsResponse(
    @field:SerializedName("homeworld") val homeWorld: String,
    @field:SerializedName("films") val films: List<String>,
    @field:SerializedName("species") val species: List<String>,
    @field:SerializedName("vehicles") val vehicles: List<String>,
    @field:SerializedName("starships") val starships: List<String>
)
