package com.arch.starwarssearch.repository

import com.arch.starwarssearch.remote.CharacterDetailsRemoteDatasource
import com.arch.starwarssearch.remote.StarWarsRequestDispatcher.Companion.CHARACTER_URL
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterDetailsRepositoryTest {
    // subject under test
    private lateinit var characterDetailsRepository: CharacterDetailsRepository

    private lateinit var characterDetailsRemoteDatasource: CharacterDetailsRemoteDatasource

    @Before
    fun setup(){
        characterDetailsRemoteDatasource = FakeCharacterDetailsRemoteDatasource()
        characterDetailsRepository = CharacterDetailsRepositoryImpl(characterDetailsRemoteDatasource)
    }

    @Test
    fun getCharacterPlanetFromRemoteDataSource_confirmPlanetRetrieved() = runBlocking {
        val result = characterDetailsRemoteDatasource.getCharacterPlanet(CHARACTER_URL)
        result.collect{ planet ->
            Truth.assertThat(planet.name).matches("Tatooine")
        }
    }

    @Test
    fun getCharacterFilmsFromRemoteDataSource_confirmFilmsRetrieved() = runBlocking {
        val result = characterDetailsRemoteDatasource.getCharacterFilms(CHARACTER_URL)
        result.collect{ films ->
            Truth.assertThat(films.size).isAtLeast(1)
            Truth.assertThat(films.first().title).matches("A New Hope")
        }
    }

    @Test
    fun getCharacterSpeciesFromRemoteDataSource_confirmSpeciesRetrieved() = runBlocking {
        val result = characterDetailsRemoteDatasource.getCharacterSpecies(CHARACTER_URL)
        result.collect{ species ->
            Truth.assertThat(species.size).isAtLeast(1)
            Truth.assertThat(species.first().name).matches("Human")
        }
    }

    @Test
    fun getCharacterStarshipsFromRemoteDataSource_confirmStarshipsRetrieved() = runBlocking {
        val result = characterDetailsRemoteDatasource.getCharacterStarShips(CHARACTER_URL)
        result.collect{ starships ->
            Truth.assertThat(starships.size).isAtLeast(1)
            Truth.assertThat(starships.first().name).matches("X-wing")
        }
    }

    @Test
    fun getCharacterVehiclesFromRemoteDataSource_confirmVehiclesRetrieved() = runBlocking {
        val result = characterDetailsRemoteDatasource.getCharacterVehicles(CHARACTER_URL)
        result.collect{ vehicles ->
            Truth.assertThat(vehicles.size).isAtLeast(1)
            Truth.assertThat(vehicles.first().name).matches("Snowspeeder")
        }
    }
}