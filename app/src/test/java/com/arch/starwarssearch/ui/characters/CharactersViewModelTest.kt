package com.arch.starwarssearch.ui.characters

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arch.starwarssearch.DataFactory
import com.arch.starwarssearch.FakeCharacterRepository
import com.arch.starwarssearch.MainCoroutineRule
import com.arch.starwarssearch.getOrAwaitValue
import com.arch.starwarssearch.mapper.toPresentation
import com.arch.starwarssearch.usecases.DeleteAllCharactersUseCase
import com.arch.starwarssearch.usecases.GetAllCharactersUseCase
import com.arch.starwarssearch.util.Result.Success
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharactersViewModelTest{
    private lateinit var charactersViewModel: CharactersViewModel

    private lateinit var characterRepository: FakeCharacterRepository

    private val char1 = DataFactory.char1
    private val char2 = DataFactory.char2

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        characterRepository = FakeCharacterRepository()
        characterRepository.addCharacters(char1, char2)
        charactersViewModel = CharactersViewModel(
            GetAllCharactersUseCase(characterRepository),
            DeleteAllCharactersUseCase(characterRepository)
        )
    }

    @Test
    fun getAllCharacters(){
        val results = charactersViewModel.getAllCharacters().getOrAwaitValue()
        results as Success
        Truth.assertThat(results.data?.isNotEmpty()).isTrue()
        Truth.assertThat(results.data).contains(char1.character.toPresentation())
        Truth.assertThat(results.data).contains(char2.character.toPresentation())
    }

    @Test
    fun deleteAllCharacters(){
        charactersViewModel.deleteAllCharacters()

        Truth.assertThat(characterRepository.charactersData.values).isEmpty()
    }
}