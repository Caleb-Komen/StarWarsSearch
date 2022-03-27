package com.arch.starwarssearch.usecases

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCharacterFilmsUseCaseTest: BaseUseCaseTest(){
    // subject under test
    private lateinit var getCharacterFilmsUseCase: GetCharacterFilmsUseCase

    override fun setup(){
        super.setup()
        getCharacterFilmsUseCase = GetCharacterFilmsUseCase(characterDetailsRepository)
    }

    @Test
    fun getCharacterFilms_confirmFilmsRetrieved() = runBlocking {
        val results = getCharacterFilmsUseCase("")
        results.collect{ films ->
            Truth.assertThat(films.size).isAtLeast(1)
            Truth.assertThat(films.first().title).matches(DataFactory.getFilm().title)
        }
    }
}