package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class SpecieInfoResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("language") val language: String
)

data class SpeciesResponse(@field:SerializedName("species") val species: List<String>)
