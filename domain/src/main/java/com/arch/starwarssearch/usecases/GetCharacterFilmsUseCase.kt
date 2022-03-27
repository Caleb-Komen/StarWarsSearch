package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.model.Film
import com.arch.starwarssearch.repository.CharacterDetailsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetCharacterFilmsUseCase @Inject constructor(
    private val characterDetailsRepository: CharacterDetailsRepository
) {
    suspend operator fun invoke(characterUrl: String): Flow<List<Film>>{
        return characterDetailsRepository.getCharacterFilms(characterUrl)
    }
}