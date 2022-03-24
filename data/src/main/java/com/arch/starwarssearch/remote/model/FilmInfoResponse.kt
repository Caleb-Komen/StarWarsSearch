package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class FilmInfoResponse(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("producer") val producer: String,
    @field:SerializedName("opening_crawl") val openingCrawl: String
)

data class FilmsResponse(@field:SerializedName("films") val films: List<String>)
