package com.arch.starwarssearch.local

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.model.CharacterWithDetails
import kotlinx.coroutines.flow.Flow

interface CharacterLocalDataSource {
    fun getCharacters(): Flow<List<Character>>

    fun getCharacterByUrl(url: String): Flow<CharacterWithDetails?>

    fun isCharacterSaved(url: String): Flow<Boolean>

    suspend fun insertCharacter(characterWithDetails: CharacterWithDetails)

    suspend fun deleteCharacterByUrl(url: String)

    suspend fun deleteCharacters()
}