package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class StarShipInfoResponse(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("model") val model: String,
    @field:SerializedName("manufacturer") val manufacturer: String,
    @field:SerializedName("passengers") val passengers: String
)

data class StarShipsResponse(@field:SerializedName("starships") val starships: List<String>)

