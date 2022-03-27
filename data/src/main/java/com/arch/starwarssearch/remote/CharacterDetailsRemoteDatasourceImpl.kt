package com.arch.starwarssearch.remote

import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.remote.api.StarWarsService
import com.arch.starwarssearch.remote.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterDetailsRemoteDatasourceImpl @Inject constructor(
    private val apiService: StarWarsService
): CharacterDetailsRemoteDatasource {
    override suspend fun getCharacterPlanet(characterUrl: String): Flow<Planet> = flow{
        val planetResponse = apiService.getPlanetResponse(characterUrl).homeWorld
        val planetInfo = apiService.getPlanetInfo(planetResponse)
        emit(planetInfo.toDomain())
    }

    override suspend fun getCharacterFilms(characterUrl: String): Flow<List<Film>> = flow{
        val filmsResponse = apiService.getFilmsResponse(characterUrl).films
        val films = mutableListOf<Film>()
        filmsResponse.forEach {
            films.add(apiService.getFilmInfo(it).toDomain())
        }
        emit(films)
    }

    override suspend fun getCharacterSpecies(characterUrl: String): Flow<List<Specie>> = flow{
        val speciesResponse = apiService.getSpeciesResponse(characterUrl).species
        val species = mutableListOf<Specie>()
        speciesResponse.forEach {
            species.add(apiService.getSpecieInfo(it).toDomain())
        }
        emit(species)
    }

    override suspend fun getCharacterVehicles(characterUrl: String): Flow<List<Vehicle>> = flow{
        val vehiclesResponse = apiService.getVehiclesResponse(characterUrl).vehicles
        val vehicles = mutableListOf<Vehicle>()
        vehiclesResponse.forEach {
            vehicles.add(apiService.getVehicleInfo(it).toDomain())
        }
        emit(vehicles)
    }

    override suspend fun getCharacterStarShips(characterUrl: String): Flow<List<StarShip>> = flow{
        val starShipsResponse = apiService.getStarShipsResponse(characterUrl).starships
        val starShips = mutableListOf<StarShip>()
        starShipsResponse.forEach {
            starShips.add(apiService.getStarshipInfo(it).toDomain())
        }
        emit(starShips)
    }
}