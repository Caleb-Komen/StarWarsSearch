package com.arch.starwarssearch.ui.charactersearch

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arch.starwarssearch.FakeCharacterSearchRepository
import com.arch.starwarssearch.MainCoroutineRule
import com.arch.starwarssearch.getOrAwaitValue
import com.arch.starwarssearch.State.ERROR
import com.arch.starwarssearch.State.SUCCESS
import com.arch.starwarssearch.usecases.SearchCharacterUseCase
import com.arch.starwarssearch.util.Result.Error
import com.arch.starwarssearch.util.Result.Success
import com.arch.starwarssearch.util.UNKNOWN_ERROR
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CharacterSearchViewModelTest {
    // subject under test
    private lateinit var characterSearchViewModel: CharacterSearchViewModel

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Test
    fun getCharacters_confirmCharactersRetrieved() = runTest{
        characterSearchViewModel = CharacterSearchViewModel(
            SearchCharacterUseCase(FakeCharacterSearchRepository(SUCCESS))
        )

        characterSearchViewModel.setSearchQuery("Luke")

        val result = characterSearchViewModel.characters.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Success::class.java)
        Truth.assertThat((result as Success).data).isNotEmpty()
    }

    @Test
    fun getCharacters_emptyQueryParam_confirmNoResults() = runTest{
        characterSearchViewModel = CharacterSearchViewModel(
            SearchCharacterUseCase(FakeCharacterSearchRepository(SUCCESS))
        )

        characterSearchViewModel.setSearchQuery("")

        val result = characterSearchViewModel.characters.getOrAwaitValue()
        Truth.assertThat((result as? Success)?.data).isNull()
    }

    @Test
    fun getCharacters_exceptionThrown_confirmNoResult() = runTest{
        characterSearchViewModel = CharacterSearchViewModel(
            SearchCharacterUseCase(FakeCharacterSearchRepository(ERROR))
        )

        characterSearchViewModel.setSearchQuery("Luke")

        val result = characterSearchViewModel.characters.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Error::class.java)
        Truth.assertThat((result as Error).message).matches(UNKNOWN_ERROR)
    }
}