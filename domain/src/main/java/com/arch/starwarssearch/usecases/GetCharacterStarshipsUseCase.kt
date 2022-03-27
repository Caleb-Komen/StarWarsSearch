package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.StarShip
import com.arch.starwarssearch.repository.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacterStarshipsUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {
    suspend operator fun invoke(characterUrl: String): Flow<List<StarShip>> {
        return characterDetailsRepository.getCharacterStarships(characterUrl)
    }
}