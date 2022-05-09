package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DeleteAllCharactersUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var deleteAllCharactersUseCase: DeleteAllCharactersUseCase

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    override fun setup() {
        super.setup()
        deleteAllCharactersUseCase = DeleteAllCharactersUseCase(characterRepository)
        characterRepository.addCharacters(char1, char2)
    }

    @Test
    fun deleteAllCharacters_emptyListOfCharactersRetrieved() = runTest {
        deleteAllCharactersUseCase()
        Truth.assertThat(characterRepository.charactersData.values.isEmpty()).isTrue()
    }
}