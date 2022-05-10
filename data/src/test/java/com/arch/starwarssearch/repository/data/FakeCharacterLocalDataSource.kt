package com.arch.starwarssearch.repository.data

import com.arch.starwarssearch.local.CharacterLocalDataSource
import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.model.CharacterWithDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

class FakeCharacterLocalDataSource(
    var characters: MutableList<CharacterWithDetails>? = mutableListOf()
): CharacterLocalDataSource {
    override fun getCharacters(): Flow<List<Character>> {
        val char = characters?.map { it.character }!!
        return flowOf(ArrayList(char))
    }

    override fun getCharacterByUrl(url: String): Flow<CharacterWithDetails?> {
        return flowOf(characters?.firstOrNull { it.character.url == url })
    }

    override fun isCharacterSaved(url: String): Flow<Boolean> {
        val character = runBlocking { getCharacterByUrl(url).first() }
        return flowOf(characters?.contains(character)!!)
    }

    override suspend fun insertCharacter(characterWithDetails: CharacterWithDetails) {
        characters?.add(characterWithDetails)
    }

    override suspend fun deleteCharacterByUrl(url: String) {
        characters?.removeIf { it.character.url == url }
    }

    override suspend fun deleteCharacters() {
        characters?.clear()
    }
}