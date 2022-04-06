package com.arch.starwarssearch

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.repository.CharacterSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharacterSearchRepository(private val state: State): CharacterSearchRepository {
    override suspend fun searchCharacter(name: String): Flow<List<Character>> {
        when (state){
            State.SUCCESS -> {
                val character = DataFactory.getCharacter()
                return flowOf(listOf(character))
            }
            State.ERROR -> throw Exception()
        }
    }
}