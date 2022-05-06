package com.arch.starwarssearch.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arch.starwarssearch.local.CharacterLocalDataSource
import com.arch.starwarssearch.local.StarWarsDatabase
import com.arch.starwarssearch.local.model.CharacterLocalDataSourceImpl
import com.arch.starwarssearch.model.*
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharacterLocalDataSourceTest {
    // subject under test
    private lateinit var characterLocalDataSource: CharacterLocalDataSource
    private lateinit var database: StarWarsDatabase

    private val character1 = Character("Luke Skywalker", "172", "19 BBY", "/api/people/1/")
    private val character2 = Character("C-3PO", "167", "112BBY", "/api/people/2/")
    private val planet = Planet("Tatooine", "120000", "1")
    private val specie = Specie("Human", "Galactic Basic")
    private val film1 = Film("A New Hope","Gary Kurtz, Rick McCallum","It is a period of civil war...")
    private val film2 = Film("The Empire Strikes Back","Gary Kurtz, Rick McCallum","It is a dark time for the Rebellion.")
    private val starship = StarShip("X-wing", "T-65 X-wing", "Incom Corporation", "0")
    private val vehicle = Vehicle("Snowspeeder", "t-47 airspeeder", "Incom corporation", "0")

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StarWarsDatabase::class.java
        ).build()
        characterLocalDataSource = CharacterLocalDataSourceImpl(database.characterDao())
    }

    @After
    fun tearDown() = database.close()

    @Test
    fun saveCharacters_retrieveCharacters() = runTest {
        val char1 = CharacterWithDetails(character1, planet, listOf(film1), listOf(specie), listOf(starship), listOf(vehicle))
        val char2 = CharacterWithDetails(character2, planet, listOf(film2), listOf(specie), listOf(starship), listOf(vehicle))
        characterLocalDataSource.insertCharacter(char1)
        characterLocalDataSource.insertCharacter(char2)

        val result = characterLocalDataSource.getCharacters()

        result.collect {
            Truth.assertThat(it.size).isEqualTo(2)
        }
    }

    @Test
    fun saveCharacter_retrieveCharacter() = runTest {
        val char = CharacterWithDetails(character1, planet, listOf(film1), listOf(specie), listOf(starship), listOf(vehicle))
        characterLocalDataSource.insertCharacter(char)

        val result = characterLocalDataSource.getCharacterByUrl(char.character.url)

        result.collect {
            Truth.assertThat(it).isNotNull()
            Truth.assertThat(it.character.name).matches("Luke Skywalker")
            Truth.assertThat(it.films.size).isAtLeast(1)
        }
    }

    @Test
    fun deleteCharacter_characterNotAvailable() = runTest {
        val char = CharacterWithDetails(character1, planet, listOf(film1), listOf(specie), listOf(starship), listOf(vehicle))
        characterLocalDataSource.insertCharacter(char)

        characterLocalDataSource.deleteCharacterByUrl(char.character.url)

        val result = characterLocalDataSource.getCharacters()
        result.collect {
            Truth.assertThat(it.isEmpty()).isTrue()
        }
    }

    @Test
    fun deleteCharacters_emptyListOfCharactersRetrieved() = runTest {
        val char1 = CharacterWithDetails(character1, planet, listOf(film1), listOf(specie), listOf(starship), listOf(vehicle))
        val char2 = CharacterWithDetails(character2, planet, listOf(film2), listOf(specie), listOf(starship), listOf(vehicle))
        characterLocalDataSource.insertCharacter(char1)
        characterLocalDataSource.insertCharacter(char2)

        characterLocalDataSource.deleteCharacters()

        val result = characterLocalDataSource.getCharacters()
        result.collect {
            Truth.assertThat(it.isEmpty()).isTrue()
        }
    }
}