package com.arch.starwarssearch.remote

import com.arch.starwarssearch.model.*
import kotlinx.coroutines.flow.Flow

interface CharacterDetailsRemoteDatasource {
    suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet>

    suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>>

    suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>>

    suspend fun getCharacterVehicles(characterUrl: String): Flow<List<Vehicle>>

    suspend fun getCharacterStarShips(characterUrl: String): Flow<List<StarShip>>
}