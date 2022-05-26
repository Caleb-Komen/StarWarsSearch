package com.arch.starwarssearch.repository

import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.remote.CharacterSearchRemoteDataSource
import com.arch.starwarssearch.util.wrapEspressoIdlingResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CharacterSearchRepositoryImpl @Inject constructor(
    private val characterSearchRemoteDataSource: CharacterSearchRemoteDataSource
): CharacterSearchRepository {
    override suspend fun searchCharacter(name: String): Flow<List<Character>> {
        wrapEspressoIdlingResource{
            return characterSearchRemoteDataSource.searchCharacter(name)
        }
    }
}