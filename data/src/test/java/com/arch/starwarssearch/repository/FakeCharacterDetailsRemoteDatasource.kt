package com.arch.starwarssearch.repository

import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.remote.CharacterDetailsRemoteDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharacterDetailsRemoteDatasource: CharacterDetailsRemoteDatasource {
    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> {
        val planet = DataFactory.getPlanet()
        return flowOf(planet)
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> {
        val film = DataFactory.getFilm()
        return flowOf(listOf(film))
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> {
        val specie = DataFactory.getSpecie()
        return flowOf(listOf(specie))
    }

    override suspend fun getCharacterVehicles(characterUrl: String): Flow<List<Vehicle>> {
        val vehicle = DataFactory.getVehicle()
        return flowOf(listOf(vehicle))
    }

    override suspend fun getCharacterStarShips(characterUrl: String): Flow<List<StarShip>> {
        val starship = DataFactory.getStarship()
        return flowOf(listOf(starship))
    }
}