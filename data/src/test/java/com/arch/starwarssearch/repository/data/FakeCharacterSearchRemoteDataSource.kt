package com.arch.starwarssearch.repository.data

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.remote.CharacterSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharacterSearchRemoteDataSource: CharacterSearchRemoteDataSource {
    override suspend fun searchCharacter(name: String): Flow<List<Character>> {
        val characters = DataFactory.getCharacter()
        return flowOf(listOf(characters))
    }
}