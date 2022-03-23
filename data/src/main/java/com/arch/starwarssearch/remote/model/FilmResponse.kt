package com.arch.starwarssearch.remote.model

import com.google.gson.annotations.SerializedName

data class FilmResponse(
    @field:SerializedName("title") val title: String,
    @field:SerializedName("producer") val producer: String,
    @field:SerializedName("opening_crawl") val openingCrawl: String
)
