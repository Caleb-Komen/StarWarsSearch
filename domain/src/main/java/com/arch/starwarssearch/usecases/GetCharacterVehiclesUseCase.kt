package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.Vehicle
import com.arch.starwarssearch.repository.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacterVehiclesUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {
    suspend operator fun invoke(characterUrl: String): Flow<List<Vehicle>> {
        return characterDetailsRepository.getCharacterVehicles(characterUrl)
    }
}