package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class SpecieResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("language") val language: String
)
