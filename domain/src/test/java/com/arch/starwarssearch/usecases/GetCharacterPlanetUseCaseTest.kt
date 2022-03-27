package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharacterPlanetUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var getCharacterPlanetUseCase: GetCharacterPlanetUseCase

    override fun setup() {
        super.setup()
        getCharacterPlanetUseCase = GetCharacterPlanetUseCase(characterDetailsRepository)
    }

    @Test
    fun getCharacterPlanet_confirmPlanetRetrieved() = runBlocking{
        val result = getCharacterPlanetUseCase("")
        result.collect{ planet ->
            Truth.assertThat(planet.name).matches(DataFactory.getPlanet().name)
        }
    }
}