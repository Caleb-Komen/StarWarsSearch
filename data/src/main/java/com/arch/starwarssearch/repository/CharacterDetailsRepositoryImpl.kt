package com.arch.starwarssearch.repository

import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.remote.CharacterDetailsRemoteDatasource
import kotlinx.coroutines.flow.Flow

class CharacterDetailsRepositoryImpl(
    private val characterDetailsRemoteDatasource: CharacterDetailsRemoteDatasource
): CharacterDetailsRepository {
    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> {
        return characterDetailsRemoteDatasource.getCharacterFilms(characterUrl)
    }

    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> {
        return characterDetailsRemoteDatasource.getCharacterPlanet(characterUrl)
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> {
        return characterDetailsRemoteDatasource.getCharacterSpecies(characterUrl)
    }

    override suspend fun getCharacterStarships(characterUrl: String): Flow<List<StarShip>> {
        return characterDetailsRemoteDatasource.getCharacterStarShips(characterUrl)
    }

    override suspend fun getCharacterVehicles(characterUrl: String): Flow<List<Vehicle>> {
        return characterDetailsRemoteDatasource.getCharacterVehicles(characterUrl)
    }
}