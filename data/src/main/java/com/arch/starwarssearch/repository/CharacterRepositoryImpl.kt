package com.arch.starwarssearch.repository

import com.arch.starwarssearch.local.CharacterLocalDataSource
import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.model.CharacterWithDetails
import com.arch.starwarssearch.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterRepositoryImpl @Inject constructor(
    private val characterLocalDataSource: CharacterLocalDataSource
): CharacterRepository {
    override fun getCharacters(): Flow<List<Character>> {
        return characterLocalDataSource.getCharacters()
    }

    override fun getCharacterByUrl(url: String): Flow<CharacterWithDetails?> {
        return characterLocalDataSource.getCharacterByUrl(url)
    }

    override fun isCharacterSaved(url: String): Flow<Boolean> {
        return characterLocalDataSource.isCharacterSaved(url)
    }

    override suspend fun insertCharacter(characterWithDetails: CharacterWithDetails) {
        characterLocalDataSource.insertCharacter(characterWithDetails)
    }

    override suspend fun deleteCharacterByUrl(url: String) {
        characterLocalDataSource.deleteCharacterByUrl(url)
    }

    override suspend fun deleteCharacters() {
        characterLocalDataSource.deleteCharacters()
    }
}