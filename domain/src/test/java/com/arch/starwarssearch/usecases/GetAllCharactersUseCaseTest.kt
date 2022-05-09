package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllCharactersUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var getAllCharactersUseCaseTest: GetAllCharactersUseCase

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    override fun setup() {
        super.setup()
        getAllCharactersUseCaseTest = GetAllCharactersUseCase(characterRepository)
        characterRepository.addCharacters(char1, char2)
    }

    @Test
    fun getAllCharacters_nonEmptyListOfCharactersRetrieved() = runTest {
        val result = getAllCharactersUseCaseTest()
        result.collect {
            Truth.assertThat(it.isNotEmpty()).isTrue()
        }
    }
}