package com.arch.starwarssearch.remote

import com.arch.starwarssearch.remote.StarWarsRequestDispatcher.Companion.EXISTING_SEARCH_PARAMS
import com.arch.starwarssearch.remote.StarWarsRequestDispatcher.Companion.NON_EXISTING_SEARCH_PARAMS
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CharacterSearchRemoteDataSourceTest : BaseTest(){

    private lateinit var characterSearchRemoteDataSource: CharacterSearchRemoteDataSource

    override fun setup() {
        super.setup()
        characterSearchRemoteDataSource = CharacterSearchRemoteDataSourceImpl(starWarsService)
    }

    @Test
    fun searchCharacter_existingSearchParam_confirmResultsRetrieved() = runBlocking {
        val response = characterSearchRemoteDataSource.searchCharacter(EXISTING_SEARCH_PARAMS)
        response.collect{ characters ->
            Truth.assertThat(characters).isNotEmpty()
        }
    }

    @Test
    fun searchCharacter_nonExistingSearchParam_confirmNoResult() = runBlocking {
        val response = characterSearchRemoteDataSource.searchCharacter(NON_EXISTING_SEARCH_PARAMS)
        response.collect{ characters ->
            Truth.assertThat(characters).isEmpty()
        }
    }
}