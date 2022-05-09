package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetCharacterUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var getCharacterUseCase: GetCharacterUseCase

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    override fun setup() {
        super.setup()
        getCharacterUseCase = GetCharacterUseCase(characterRepository)
        characterRepository.addCharacters(char1, char2)
    }

    @Test
    fun getCharacter_confirmCharacterIsRetrieved() = runTest {
        val result = getCharacterUseCase(char1.character.url)
        result.collect {
            Truth.assertThat(it).isEqualTo(char1)
        }
    }
}