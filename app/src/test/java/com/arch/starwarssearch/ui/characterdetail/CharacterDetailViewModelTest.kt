package com.arch.starwarssearch.ui.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arch.starwarssearch.FakeCharacterDetailsRepository
import com.arch.starwarssearch.MainCoroutineRule
import com.arch.starwarssearch.getOrAwaitValue
import com.arch.starwarssearch.usecases.*
import com.arch.starwarssearch.util.Result.Success
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class CharacterDetailViewModelTest{
    // subject under test
    private lateinit var characterDetailViewModel: CharacterDetailViewModel

    @get:Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup(){
        characterDetailViewModel = CharacterDetailViewModel(
            GetCharacterPlanetUseCase(FakeCharacterDetailsRepository()),
            GetCharacterFilmsUseCase(FakeCharacterDetailsRepository()),
            GetCharacterSpeciesUseCase(FakeCharacterDetailsRepository()),
            GetCharacterStarshipsUseCase(FakeCharacterDetailsRepository()),
            GetCharacterVehiclesUseCase(FakeCharacterDetailsRepository())
        )
    }

    @Test
    fun getCharacterPlanet_confirmPlanetRetrieved() = runTest {
        characterDetailViewModel.setCharacterUrl("")

        val result = characterDetailViewModel.planet.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Success::class.java)
        Truth.assertThat((result as Success).data?.name).matches("Tatooine")
    }

    @Test
    fun getCharacterFilms_confirmFilmsRetrieved() = runTest {
        characterDetailViewModel.setCharacterUrl("")

        val result = characterDetailViewModel.films.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Success::class.java)
        Truth.assertThat((result as Success).data?.size).isAtLeast(1)
    }

    @Test
    fun getCharacterSpecies_confirmSpeciesRetrieved() = runTest {
        characterDetailViewModel.setCharacterUrl("")

        val result = characterDetailViewModel.species.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Success::class.java)
        Truth.assertThat((result as Success).data?.size).isAtLeast(1)
    }

    @Test
    fun getCharacterStarships_confirmStarshipsRetrieved() = runTest {
        characterDetailViewModel.setCharacterUrl("")

        val result = characterDetailViewModel.starships.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Success::class.java)
        Truth.assertThat((result as Success).data).isNotEmpty()
    }

    @Test
    fun getCharacterVehicles_confirmVehiclesRetrieved() = runTest {
        characterDetailViewModel.setCharacterUrl("")

        val result = characterDetailViewModel.vehicles.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Success::class.java)
        Truth.assertThat((result as Success).data).isNotEmpty()
    }
}