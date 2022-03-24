package com.arch.starwarssearch.remote.api

import com.arch.starwarssearch.remote.model.*
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface StarWarsService {
    @GET("people/")
    suspend fun searchCharacter(@Query("search") name: String): StarWarsSearchResponse

    @GET
    suspend fun getFilmsResponse(@Url characterUrl: String): FilmsResponse

    @GET
    suspend fun getFilmInfo(@Url filmUrl: String): FilmInfoResponse

    @GET
    suspend fun getPlanetResponse(@Url characterUrl: String): PlanetResponse

    @GET
    suspend fun getPlanetInfo(@Url planetUrl: String): PlanetInfoResponse

    @GET
    suspend fun getSpeciesResponse(@Url characterUrl: String): SpeciesResponse
    @GET
    suspend fun getSpecieInfo(@Url specieUrl: String): SpecieInfoResponse

    @GET
    suspend fun getVehiclesResponse(@Url characterUrl: String): VehiclesResponse

    @GET
    suspend fun getVehicleInfo(@Url vehicleUrl: String): VehicleInfoResponse

    @GET
    suspend fun getStarShipsResponse(@Url characterUrl: String): StarShipsResponse
    @GET
    suspend fun getStarshipInfo(@Url starShipUrl: String): StarShipInfoResponse
}