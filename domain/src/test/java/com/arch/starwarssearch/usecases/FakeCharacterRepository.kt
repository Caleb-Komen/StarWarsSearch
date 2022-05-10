package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.model.CharacterWithDetails
import com.arch.starwarssearch.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeCharacterRepository: CharacterRepository {
    var charactersData: LinkedHashMap<String, CharacterWithDetails> = LinkedHashMap()

    fun addCharacters(vararg characters: CharacterWithDetails){
        for (char in characters){
            charactersData[char.character.url] = char
        }
    }

    override fun getCharacters(): Flow<List<Character>> {
        return flowOf(charactersData.values.map { it.character }.toList())
    }

    override fun getCharacterByUrl(url: String): Flow<CharacterWithDetails?> {
        return flowOf(charactersData[url])
    }

    override fun isCharacterSaved(url: String): Flow<Boolean> {
        return flowOf(charactersData.containsKey(url))
    }

    override suspend fun insertCharacter(characterWithDetails: CharacterWithDetails) {
        charactersData[characterWithDetails.character.url] = characterWithDetails
    }

    override suspend fun deleteCharacterByUrl(url: String) {
       charactersData.remove(url)
    }

    override suspend fun deleteCharacters() {
        charactersData.clear()
    }
}