package com.arch.starwarssearch.ui.characterdetail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.arch.starwarssearch.DataFactory
import com.arch.starwarssearch.FakeCharacterDetailsRepository
import com.arch.starwarssearch.FakeCharacterRepository
import com.arch.starwarssearch.MainCoroutineRule
import com.arch.starwarssearch.getOrAwaitValue
import com.arch.starwarssearch.mapper.toPresentation
import com.arch.starwarssearch.usecases.*
import com.arch.starwarssearch.util.Result
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
        characterRepository.addCharacters(char1)

        characterDetailViewModel = CharacterDetailViewModel(
            GetCharacterPlanetUseCase(FakeCharacterDetailsRepository()),
            GetCharacterFilmsUseCase(FakeCharacterDetailsRepository()),
            GetCharacterSpeciesUseCase(FakeCharacterDetailsRepository()),
            GetCharacterStarshipsUseCase(FakeCharacterDetailsRepository()),
            GetCharacterVehiclesUseCase(FakeCharacterDetailsRepository()),
            GetCharacterUseCase(characterRepository),
            SaveCharacterUseCase(characterRepository),
            CharacterSavedUseCase(characterRepository),
            DeleteCharacterUseCase(characterRepository)
        )
    }

    @Test
    fun saveCharacter() = runTest {
        characterDetailViewModel.setCharacterUrl(char2.character.url)

        // wait for the livedata to be set
        characterDetailViewModel.planet.getOrAwaitValue()
        characterDetailViewModel.species.getOrAwaitValue()
        characterDetailViewModel.films.getOrAwaitValue()
        characterDetailViewModel.starships.getOrAwaitValue()
        characterDetailViewModel.vehicles.getOrAwaitValue()

        characterDetailViewModel.saveCharacter(char2.character.toPresentation())

        val result = characterRepository.charactersData[char2.character.url]
        Truth.assertThat(result).isNotNull()
    }

    @Test
    fun getCharacter() = runTest {
        characterDetailViewModel.setCharacterUrl(char1.character.url)

        val result = characterDetailViewModel.character.getOrAwaitValue()
        Truth.assertThat(result).isInstanceOf(Success::class.java)
        result as Success
        Truth.assertThat(result.data).isEqualTo(char1.toPresentation())
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

    @Test
    fun deleteCharacter() = runTest {
        characterDetailViewModel.deleteCharacter(char1.character.url)

        val result = characterRepository.charactersData[char1.character.url]
        Truth.assertThat(result).isNull()
    }
}