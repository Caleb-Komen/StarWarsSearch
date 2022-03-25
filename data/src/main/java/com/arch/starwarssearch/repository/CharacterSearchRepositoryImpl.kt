package com.arch.starwarssearch.repository

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.remote.CharacterSearchRemoteDataSource
import kotlinx.coroutines.flow.Flow

class CharacterSearchRepositoryImpl(
    private val characterSearchRemoteDataSource: CharacterSearchRemoteDataSource
): CharacterSearchRepository {
    override suspend fun searchCharacter(name: String): Flow<List<Character>> {
        return characterSearchRemoteDataSource.searchCharacter(name)
    }
}