package com.arch.starwarssearch.repository

import com.arch.starwarssearch.local.CharacterLocalDataSource
import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.repository.data.DataFactory
import com.arch.starwarssearch.repository.data.FakeCharacterLocalDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterRepositoryTest{
    // subject under test
    private lateinit var characterRepository: CharacterRepository

    private lateinit var characterLocalDataSource: CharacterLocalDataSource

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    @Before
    fun setup(){
        characterLocalDataSource = FakeCharacterLocalDataSource(mutableListOf(char1, char2))
        characterRepository = CharacterRepositoryImpl(characterLocalDataSource)
    }

    @Test
    fun getCharacters_confirmCharactersAvailable() = runTest {
        // When we retrieve all characters
        val result = characterLocalDataSource.getCharacters()

        // Then a non-empty list of characters is retrieved
        result.collect {
            Truth.assertThat(it.isNotEmpty()).isTrue()
        }
    }

    @Test
    fun getCharacterByUrl_confirmCharacterAvailable() = runTest {
        // When a character is retrieved
        val result = characterLocalDataSource.getCharacterByUrl(char1.character.url)

        // Then a non-null character is retrieved
        result.collect {
            Truth.assertThat(it).isNotNull()
            Truth.assertThat(it).isEqualTo(char1)
        }
    }

    @Test
    fun saveCharacter_confirmCharacterIsSaved() = runTest {
        // When we save a new character
        val char = CharacterWithDetails(
            Character("R2-D2", "96", "33BBY", "/api/people/3/"),
            DataFactory.getPlanet(),
            listOf(DataFactory.getFilm()),
            listOf(DataFactory.getSpecie()),
            listOf(DataFactory.getStarship()),
            listOf(DataFactory.getVehicle())
        )
        characterLocalDataSource.insertCharacter(char)

        // Then the character is available in the local source
        val result = characterLocalDataSource.isCharacterSaved(char.character.url)
        result.collect {
            Truth.assertThat(it).isTrue()
        }
    }

    @Test
    fun deleteCharacterByUrl_confirmCharacterNotAvailable() = runTest {
        characterLocalDataSource.deleteCharacterByUrl(char1.character.url)

        val result = characterLocalDataSource.getCharacters()
        result.collect {
            Truth.assertThat(it).doesNotContain(char1)
        }
    }

    @Test
    fun deleteCharacters_confirmEmptyListOfCharactersRetrieved() = runTest {
        characterLocalDataSource.deleteCharacters()

        val result = characterLocalDataSource.getCharacters()
        result.collect {
            Truth.assertThat(it.isEmpty()).isTrue()
        }
    }
}