package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class StarWarsSearchResponse(
    @field:SerializedName("next") val next: String?,
    @field:SerializedName("previous") val previous: String?,
    @field:SerializedName("results") val results: List<CharacterResponse>
)
