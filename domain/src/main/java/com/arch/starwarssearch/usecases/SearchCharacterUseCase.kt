package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.repository.CharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchCharacterUseCase @Inject constructor(
    private val characterSearchRepository: CharacterSearchRepository
) {
    suspend operator fun invoke(name: String): Flow<List<Character>>{
        return characterSearchRepository.searchCharacter(name)
    }
}