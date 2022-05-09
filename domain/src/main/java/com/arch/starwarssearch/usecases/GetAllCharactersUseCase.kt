package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAllCharactersUseCase @Inject constructor(
    private val characterRepository: CharacterRepository
) {
    operator fun invoke(): Flow<List<Character>>{
        return characterRepository.getCharacters()
    }
}