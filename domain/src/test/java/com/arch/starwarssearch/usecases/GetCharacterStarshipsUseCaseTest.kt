package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharacterStarshipsUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var getCharacterStarshipsUseCase: GetCharacterStarshipsUseCase

    override fun setup() {
        super.setup()
        getCharacterStarshipsUseCase = GetCharacterStarshipsUseCase(characterDetailsRepository)
    }

    @Test
    fun getCharacterStarships_confirmStarshipsRetrieved() = runBlocking {
        val result = getCharacterStarshipsUseCase("")
        result.collect{ starships ->
            Truth.assertThat(starships.size).isAtLeast(1)
            Truth.assertThat(starships.first().name).matches(DataFactory.getStarship().name)
        }
    }
}