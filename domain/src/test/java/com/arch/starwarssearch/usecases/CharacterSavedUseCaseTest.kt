package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterSavedUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var characterSavedUseCase: CharacterSavedUseCase

    private val char1 = DataFactory.char1

    override fun setup() {
        super.setup()
        characterSavedUseCase = CharacterSavedUseCase(characterRepository)
        characterRepository.addCharacters(char1)
    }

    @Test
    fun deleteAllCharacters_emptyListOfCharactersRetrieved() = runTest {
        val result = characterSavedUseCase(char1.character.url)
        result.collect {
            Truth.assertThat(it).isTrue()
        }
    }
}