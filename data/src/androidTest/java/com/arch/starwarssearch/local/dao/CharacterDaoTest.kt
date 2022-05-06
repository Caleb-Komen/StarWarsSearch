package com.arch.starwarssearch.local.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.arch.starwarssearch.local.StarWarsDatabase
import com.arch.starwarssearch.local.model.*
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CharacterDaoTest {
    private lateinit var database: StarWarsDatabase
    private lateinit var characterDao: CharacterDao

    private val characterEntity1 = CharacterLocalEntity("Luke Skywalker", "172", "19 BBY", "/api/people/1/")
    private val characterEntity2 = CharacterLocalEntity("C-3PO", "167", "112BBY", "/api/people/2/")
    private val planet1 = PlanetLocalEntity("Tatooine", "120000", "1", characterEntity1.url)
    private val planet2 = PlanetLocalEntity("Tatooine", "120000", "1", characterEntity2.url)
    private val specie = SpecieLocalEntity("Human", "Galactic Basic", characterEntity1.url)
    private val film1 = FilmLocalEntity("A New Hope","Gary Kurtz, Rick McCallum","It is a period of civil war...", characterEntity1.url)
    private val film2 = FilmLocalEntity("The Empire Strikes Back","Gary Kurtz, Rick McCallum","It is a dark time for the Rebellion.", characterEntity2.url)
    private val starship = StarshipLocalEntity("X-wing", "T-65 X-wing", "Incom Corporation", "0", characterEntity1.url)
    private val vehicle = VehicleLocalEntity("Snowspeeder", "t-47 airspeeder", "Incom corporation", "0", characterEntity1.url)

    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            StarWarsDatabase::class.java
        ).build()
    characterDao = database.characterDao()
    }

    @After
    fun tearDown() = database.close()

    @Test
    fun insertCharactersAndGetAllCharacters()= runTest {
        // Given that we save characters
        val character1 = CharacterWithDetailsLocalEntity(characterEntity1, planet1, listOf(film1), listOf(specie), listOf(starship), listOf(vehicle))
        val character2 = CharacterWithDetailsLocalEntity(characterEntity2, planet2, listOf(film2), emptyList(), emptyList(), emptyList())
        characterDao.insertCharacter(character1)
        characterDao.insertCharacter(character2)

        // When we get all the characters from the database
        val result = characterDao.getCharacters()

        // Then the loaded data contains the expected value
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result.size).isEqualTo(2)
    }

    @Test
    fun insertCharacterAndGetByUrl() = runTest {
        // Given that we save a character with all its details
        val character = CharacterWithDetailsLocalEntity(
            characterEntity1,
            planet1,
            listOf(film1),
            listOf(specie),
            listOf(starship),
            listOf(vehicle)
        )
        characterDao.insertCharacter(character)

        // When we get the character by its url from the database
        val result = characterDao.getCharacterByUrl(character.characterLocalEntity.url)

        // Then the loaded character contains the expected values
        Truth.assertThat(result).isNotNull()
        Truth.assertThat(result.characterLocalEntity.name).matches("Luke Skywalker")
        Truth.assertThat(result.planetLocalEntity.name).matches("Tatooine")
        Truth.assertThat(result.specieLocalEntities.size).isAtLeast(1)
        Truth.assertThat(result.filmLocalEntities.size).isAtLeast(1)
        Truth.assertThat(result.starshipLocalEntities.size).isAtLeast(1)
        Truth.assertThat(result.vehicleLocalEntities.size).isAtLeast(1)
    }

    @Test
    fun deleteCharacterByUrlAndGetByUrl() = runTest {
        // Given that we save a character with all its details
        val character = CharacterWithDetailsLocalEntity(
            characterEntity1,
            planet1,
            listOf(film1),
            listOf(specie),
            listOf(starship),
            listOf(vehicle)
        )
        characterDao.insertCharacter(character)

        // When we delete the character by its url from the database
        characterDao.deleteCharacterByUrl(character.characterLocalEntity.url)

        // Then the character is removed completely
        val result = characterDao.getCharacters()
        Truth.assertThat(result.isEmpty()).isTrue()
    }

    @Test
    fun deleteCharactersAndGetCharacters()= runTest {
        // Given that we save characters
        val character1 = CharacterWithDetailsLocalEntity(characterEntity1, planet1, listOf(film1), listOf(specie), listOf(starship), listOf(vehicle))
        val character2 = CharacterWithDetailsLocalEntity(characterEntity2, planet2, listOf(film2), emptyList(), emptyList(), emptyList())
        characterDao.insertCharacter(character1)
        characterDao.insertCharacter(character2)

        // When we delete all the characters from the database
        characterDao.deleteAll()

        // Then all data saved into the database is removed completely
        val result = characterDao.getCharacters()
        Truth.assertThat(result.isEmpty()).isTrue()
    }
}