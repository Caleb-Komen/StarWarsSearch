package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharacterSpeciesUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var getCharacterSpeciesUseCase: GetCharacterSpeciesUseCase

    override fun setup() {
        super.setup()
        getCharacterSpeciesUseCase = GetCharacterSpeciesUseCase(characterDetailsRepository)
    }

    @Test
    fun getCharacterSpecies_confirmSpeciesRetrieved() = runBlocking {
        val result = getCharacterSpeciesUseCase("")
        result.collect{ species ->
            Truth.assertThat(species.size).isAtLeast(1)
            Truth.assertThat(species.first().name).matches(DataFactory.getSpecie().name)
        }
    }
}