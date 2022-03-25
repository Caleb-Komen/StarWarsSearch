package com.arch.starwarssearch.remote

import com.arch.starwarssearch.remote.StarWarsRequestDispatcher.Companion.CHARACTER_URL
import com.google.common.truth.Truth
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterDetailsRemoteDatasourceTest: BaseTest() {
    private lateinit var characterDetailsRemoteDatasource: CharacterDetailsRemoteDatasource

    override fun setup() {
        super.setup()
        characterDetailsRemoteDatasource = CharacterDetailsRemoteDatasourceImpl(starWarsService)
    }

    @Test
    fun getCharacterPlanet_confirmPlanetRetrieved() = runBlocking {
        val response = characterDetailsRemoteDatasource.getCharacterPlanet(CHARACTER_URL)
        response.collect{ planet ->
            Truth.assertThat(planet.name).matches("Tatooine")
        }
    }

    @Test
    fun getCharacterFilms_confirmFilmsRetrieved() = runBlocking {
        val response = characterDetailsRemoteDatasource.getCharacterFilms(CHARACTER_URL)
        response.collect{ films ->
            Truth.assertThat(films.size).isAtLeast(1)
        }
    }

    @Test
    fun getCharacterSpecies_confirmSpeciesRetrieved() = runBlocking {
        val response = characterDetailsRemoteDatasource.getCharacterSpecies(CHARACTER_URL)
        response.collect{ species ->
            Truth.assertThat(species.size).isAtLeast(1)
        }
    }

    @Test
    fun getCharacterStarships_confirmStarshipsRetrieved() = runBlocking {
        val response = characterDetailsRemoteDatasource.getCharacterStarShips(CHARACTER_URL)
        response.collect{ starships ->
            Truth.assertThat(starships.size).isAtLeast(1)
        }
    }

    @Test
    fun getCharacterVehicles_confirmVehiclesRetrieved() = runBlocking {
        val response = characterDetailsRemoteDatasource.getCharacterVehicles(CHARACTER_URL)
        response.collect{ vehicles ->
            Truth.assertThat(vehicles)
        }
    }
}