package com.arch.starwarssearch.repository

import com.arch.starwarssearch.model.*
import kotlinx.coroutines.flow.Flow

interface CharacterDetailsRepository {
    suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>>

    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>

    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>>

    suspend fun getCharacterStarships(characterUrl: String): Flow<List<StarShip>>

    suspend fun getCharacterVehicles(characterUrl: String): Flow<List<Vehicle>>
}