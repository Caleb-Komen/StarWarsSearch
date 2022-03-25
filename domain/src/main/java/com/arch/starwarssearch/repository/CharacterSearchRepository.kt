package com.arch.starwarssearch.repository

import com.arch.starwarssearch.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterSearchRepository {
    suspend fun searchCharacter(name: String): Flow<List<Character>>
}