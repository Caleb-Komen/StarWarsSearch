package com.arch.starwarssearch.remote

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.remote.api.StarWarsService
import com.arch.starwarssearch.remote.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterSearchRemoteDataSourceImpl(
    private val apiService: StarWarsService
): CharacterSearchRemoteDataSource {
    override suspend fun searchCharacter(name: String): Flow<List<Character>> = flow{
        val response = apiService.searchCharacter(name).results
        val characters = response.map {
            it.toDomain()
        }
        emit(characters)
    }
}