package com.arch.starwarssearch.remote

import com.arch.starwarssearch.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterSearchRemoteDataSource {
    suspend fun searchCharacter(name: String): Flow<List<Character>>
}