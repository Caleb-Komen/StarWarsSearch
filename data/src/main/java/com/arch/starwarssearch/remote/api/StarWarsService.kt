package com.arch.starwarssearch.remote.api

import com.arch.starwarssearch.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsService {
    @GET("people/")
    suspend fun searchCharacters(@Query("search") name: String): StarWarsSearchResponse

    @GET
    suspend fun getCharacterDetails(@Url characterUrl: String): CharacterDetailsResponse

    @GET
    suspend fun getFilm(@Url filmUrl: String): FilmResponse

    @GET
    suspend fun getPlanet(@Url planetUrl: String): PlanetResponse

    @GET
    suspend fun getSpecie(@Url specieUrl: String): SpecieResponse

    @GET
    suspend fun getVehicle(@Url vehicleUrl: String): VehicleResponse

    @GET
    suspend fun getStarship(@Url starShipUrl: String): StarShipResponse
}