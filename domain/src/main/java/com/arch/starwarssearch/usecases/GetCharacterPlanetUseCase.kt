package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.Planet
import com.arch.starwarssearch.repository.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacterPlanetUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {
    suspend operator fun invoke(characterUrl: String): Flow<Planet> {
        return characterDetailsRepository.getCharacterPlanet(characterUrl)
    }
}