package com.arch.starwarssearch.usecases

import com.arch.starwarssearch.repository.CharacterDetailsRepository
import com.arch.starwarssearch.repository.CharacterSearchRepository
import org.junit.Before

open class BaseUseCaseTest {
    lateinit var characterSearchRepository: CharacterSearchRepository

    lateinit var characterDetailsRepository: CharacterDetailsRepository

    lateinit var characterRepository: FakeCharacterRepository

    @Before
    open fun setup(){
        characterSearchRepository = FakeCharacterSearchRepository()
        characterDetailsRepository = FakeCharacterDetailsRepository()
        characterRepository = FakeCharacterRepository()
    }
}