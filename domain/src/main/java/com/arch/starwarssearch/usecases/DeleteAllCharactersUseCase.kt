package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.repository.CharacterRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteAllCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    suspend operator fun invoke(){
        characterRepository.deleteCharacters()
    }
}