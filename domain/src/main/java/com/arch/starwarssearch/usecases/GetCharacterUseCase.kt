package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.CharacterWithDetails
import com.arch.starwarssearch.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(url: String): Flow<CharacterWithDetails?> {
        return characterRepository.getCharacterByUrl(url)
    }
}