package com.arch.starwarssearch.repository

import com.arch.starwarssearch.remote.CharacterSearchRemoteDataSource
import com.arch.starwarssearch.repository.data.FakeCharacterSearchRemoteDataSource
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CharacterSearchRepositoryTest {
    // subject under test
    private lateinit var characterSearchRepository: CharacterSearchRepository

    private lateinit var characterSearchRemoteDataSource: CharacterSearchRemoteDataSource

    @Before
    fun setup(){
        characterSearchRemoteDataSource = FakeCharacterSearchRemoteDataSource()
        characterSearchRepository = CharacterSearchRepositoryImpl(characterSearchRemoteDataSource)
    }

    @Test
    fun searchCharacterFromRemoteDataSource_confirmCharacterRetrieved() = runBlocking {
        val results = characterSearchRepository.searchCharacter("")
        results.collect{ characters ->
            Truth.assertThat(characters).isNotEmpty()
            Truth.assertThat(characters.first().name).matches("Luke Skywalker")
        }
    }
}