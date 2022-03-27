package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharacterVehiclesUseCaseTest: BaseUseCaseTest() {
    // subject under test
    private lateinit var getCharacterVehiclesUseCase: GetCharacterVehiclesUseCase

    override fun setup() {
        super.setup()
        getCharacterVehiclesUseCase = GetCharacterVehiclesUseCase(characterDetailsRepository)
    }

    @Test
    fun getCharacterVehicles_confirmVehiclesRetrieved() = runBlocking {
        val result = getCharacterVehiclesUseCase("")
        result.collect{ vehicles ->
            Truth.assertThat(vehicles.size).isAtLeast(1)
            Truth.assertThat(vehicles.first().name).matches(DataFactory.getVehicle().name)
        }
    }
}