package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SearchCharacterUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var searchCharacterUseCase: SearchCharacterUseCase

    override fun setup(){
        super.setup()
        searchCharacterUseCase = SearchCharacterUseCase(characterSearchRepository)
    }

    @Test
    fun searchCharacter_confirmResultsRetrieved() = runBlocking {
        val results = searchCharacterUseCase("")
        results.collect{ characters ->
            Truth.assertThat(characters.size).isAtLeast(1)
            Truth.assertThat(characters.first().name).matches(DataFactory.getCharacter().name)
        }
    }
}