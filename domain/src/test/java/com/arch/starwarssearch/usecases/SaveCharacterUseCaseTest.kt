package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SaveCharacterUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var saveCharacterUseCase: SaveCharacterUseCase

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    override fun setup() {
        super.setup()
        saveCharacterUseCase = SaveCharacterUseCase(characterRepository)
        characterRepository.addCharacters(char1)
    }

    @Test
    fun saveCharacterIntoRepository() = runTest {
        saveCharacterUseCase(char2)
        Truth.assertThat(characterRepository.charactersData.values).contains(char2)
    }
}