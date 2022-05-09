package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DeleteCharacterUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var deleteCharacterUseCase: DeleteCharacterUseCase

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    override fun setup() {
        super.setup()
        deleteCharacterUseCase = DeleteCharacterUseCase(characterRepository)
        characterRepository.addCharacters(char1, char2)
    }

    @Test
    fun deleteCharacter_confirmCharacterNotAvailable() = runTest {
        deleteCharacterUseCase(char1.character.url)
        Truth.assertThat(characterRepository.charactersData.values).doesNotContain(char1)
    }
}