package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.CharacterWithDetails
import com.arch.starwarssearch.repository.CharacterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(characterWithDetails: CharacterWithDetails){
        characterRepository.insertCharacter(characterWithDetails)
    }
}