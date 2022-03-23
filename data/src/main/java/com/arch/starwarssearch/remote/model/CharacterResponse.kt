package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("height") val height: String,
    @field:SerializedName("birth_year") val birthYear: String,
    @field:SerializedName("url") val url: String
)
