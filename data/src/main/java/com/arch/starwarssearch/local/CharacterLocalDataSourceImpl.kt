package com.arch.starwarssearch.local

import com.arch.starwarssearch.local.dao.CharacterDao
import com.arch.starwarssearch.local.mapper.toDomain
import com.arch.starwarssearch.local.mapper.toEntity
import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.model.CharacterWithDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterLocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao
): CharacterLocalDataSource {
    override fun getCharacters(): Flow<List<Character>> = flow{
        val characters = characterDao.getCharacters().map { it.toDomain() }
        emit(characters)
    }

    override fun getCharacterByUrl(url: String): Flow<CharacterWithDetails?> = flow{
        val character = characterDao.getCharacterByUrl(url)?.toDomain()
        emit(character)
    }

    override fun isCharacterSaved(url: String): Flow<Boolean> = flow {
        emit(characterDao.isCharacterSaved(url))
    }

    override suspend fun insertCharacter(characterWithDetails: CharacterWithDetails) {
        characterDao.insertCharacter(characterWithDetails.toEntity())
    }

    override suspend fun deleteCharacterByUrl(url: String) {
        characterDao.deleteCharacterByUrl(url)
    }

    override suspend fun deleteCharacters() {
        characterDao.deleteAll()
    }
}