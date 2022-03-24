package com.arch.starwarssearch.remote

import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.remote.api.StarWarsService
import com.arch.starwarssearch.remote.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterDetailsDatasourceImpl(
    private val apiService: StarWarsService
): CharacterDetailsDatasource {
    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> = flow{
        val planetResponse = apiService.getPlanetResponse(characterUrl).homeWorld
        val planetInfo = apiService.getPlanetInfo(planetResponse)
        emit(planetInfo.toDomain())
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> = flow{
        val filmsResponse = apiService.getFilmsResponse(characterUrl).films
        val films = mutableListOf<Film>()
        filmsResponse.forEach {
            apiService.getFilmInfo(it).toDomain()
        }
        emit(films)
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> = flow{
        val speciesResponse = apiService.getSpeciesResponse(characterUrl).species
        val species = mutableListOf<Specie>()
        speciesResponse.forEach {
            apiService.getSpecieInfo(it).toDomain()
        }
        emit(species)
    }

    override suspend fun getCharacterVehicles(characterUrl: String): Flow<List<Vehicle>> = flow{
        val vehiclesResponse = apiService.getVehiclesResponse(characterUrl).vehicles
        val vehicles = mutableListOf<Vehicle>()
        vehiclesResponse.forEach {
            apiService.getVehicleInfo(it).toDomain()
        }
        emit(vehicles)
    }

    override suspend fun getCharacterStarShips(characterUrl: String): Flow<List<StarShip>> = flow{
        val starShipsResponse = apiService.getStarShipsResponse(characterUrl).starships
        val starShips = mutableListOf<StarShip>()
        starShipsResponse.forEach {
            apiService.getStarshipInfo(it).toDomain()
        }
        emit(starShips)
    }
}