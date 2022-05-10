package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterSavedUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(url: String): Flow<Boolean>{
        return characterRepository.isCharacterSaved(url)
    }
}