package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.repository.CharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharacterSearchRepository: CharacterSearchRepository {
    override suspend fun searchCharacter(name: String): Flow<List<Character>> {
        val character = DataFactory.getCharacter()
        return flowOf(listOf(character))
    }
}