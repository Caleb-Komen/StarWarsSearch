package com.arch.starwarssearch.ui.characterdetail

import androidx.lifecycle.*
import com.arch.starwarssearch.mapper.toDomain
import com.arch.starwarssearch.mapper.toPresentation
import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.usecases.*
import com.arch.starwarssearch.util.NO_INTERNET
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.Success
import com.arch.starwarssearch.util.UNKNOWN_ERROR
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterPlanetUseCase: GetCharacterPlanetUseCase,
    private val getCharacterFilmsUseCase: GetCharacterFilmsUseCase,
    private val getCharacterSpeciesUseCase: GetCharacterSpeciesUseCase,
    private val getCharacterStarshipsUseCase: GetCharacterStarshipsUseCase,
    private val getCharacterVehiclesUseCase: GetCharacterVehiclesUseCase,
    private val getCharacterUseCase: GetCharacterUseCase,
    private val saveCharacterUseCase: SaveCharacterUseCase,
    private val characterSavedUseCase: CharacterSavedUseCase,
    private val deleteCharacterUseCase: DeleteCharacterUseCase
): ViewModel() {
    private val characterUrl = MutableLiveData<String>()

    private val _planet = characterUrl.switchMap { getCharacterPlanet(it) }
    val planet: LiveData<Result<PlanetPresentation>> get() = _planet

    private val _films = characterUrl.switchMap { getCharacterFilms(it) }
    val films: LiveData<Result<List<FilmPresentation>>> get() = _films

    private val _species = characterUrl.switchMap { getCharacterSpecies(it) }
    val species: LiveData<Result<List<SpeciePresentation>>> get() = _species

    private val _starships = characterUrl.switchMap { getCharacterStarships(it) }
    val starships: LiveData<Result<List<StarshipPresentation>>> get() = _starships

    private val _vehicles = characterUrl.switchMap { getCharacterVehicles(it) }
    val vehicles: LiveData<Result<List<VehiclePresentation>>> get() = _vehicles

    val isCharacterSaved = MutableLiveData(false)

    private val loadCharacter = MutableLiveData<String>()

    private val _character = loadCharacter.switchMap { getCharacter(it) }
    val character: LiveData<Result<CharacterWithDetailsPresentation>> get() = _character

    fun setCharacterUrl(url: String){
        var characterSaved: Boolean
        viewModelScope.launch {
            characterSaved = isCharacterInLocalDb(url)
            if (characterSaved){
                isCharacterSaved.value = true
                loadCharacter.value = url
            } else {
                characterUrl.value = url
                isCharacterSaved.value = false
            }
        }
    }

    fun saveCharacter(char: CharacterPresentation){
        val character = CharacterWithDetailsPresentation(
            character = char,
            planet = (_planet.value as Success).data!!,
            films = (_films.value as Success).data!!,
            species = (_species.value as Success).data!!,
            starships = (_starships.value as Success).data!!,
            vehicles = (_vehicles.value as Success).data!!
        )
        viewModelScope.launch {
            saveCharacterUseCase(character.toDomain())
            // update the LiveData once a character is saved to help redraw the action bar menu
            isCharacterSaved.value = isCharacterInLocalDb(char.url)
        }
    }

    private suspend fun isCharacterInLocalDb(url: String): Boolean{
        var isSaved = false
        characterSavedUseCase(url).collect {
            isSaved = it
        }
        return isSaved
    }

    private fun getCharacter(url: String): LiveData<Result<CharacterWithDetailsPresentation>>{
        val result = MutableLiveData<Result<CharacterWithDetailsPresentation>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            result.value = Result.Error(throwable.message)
        }
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterUseCase(url).collect {
                result.value = Success(it?.toPresentation())
            }
        }
        return result
    }

    private fun getCharacterPlanet(url: String):LiveData<Result<PlanetPresentation>> {
        val result = MutableLiveData<Result<PlanetPresentation>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            when (throwable){
                is UnknownHostException -> result.value = Result.Error(NO_INTERNET)
                else -> result.value = Result.Error(UNKNOWN_ERROR)
            }
        }
        viewModelScope.launch(coroutineExceptionHandler) {
            result.value = Result.Loading
            getCharacterPlanetUseCase(url).collect{ planet ->
                result.value = Success(planet.toPresentation())
            }
        }
        return result
    }

    private fun getCharacterFilms(url: String): LiveData<Result<List<FilmPresentation>>>{
        val result = MutableLiveData<Result<List<FilmPresentation>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            when (throwable){
                is UnknownHostException -> result.value = Result.Error(NO_INTERNET)
                else -> result.value = Result.Error(UNKNOWN_ERROR)
            }
        }
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterFilmsUseCase(url).collect{ films ->
                result.value = Success(films.map { it.toPresentation() })
            }
        }
        return result
    }

    private fun getCharacterSpecies(url: String): LiveData<Result<List<SpeciePresentation>>>{
        val result = MutableLiveData<Result<List<SpeciePresentation>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            when (throwable){
                is UnknownHostException -> result.value = Result.Error(NO_INTERNET)
                else -> result.value = Result.Error(UNKNOWN_ERROR)
            }
        }
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterSpeciesUseCase(url).collect{ species ->
                result.value = Success(species.map { it.toPresentation() })
            }
        }
        return result
    }

    private fun getCharacterStarships(url: String): LiveData<Result<List<StarshipPresentation>>>{
        val result = MutableLiveData<Result<List<StarshipPresentation>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            when (throwable){
                is UnknownHostException -> result.value = Result.Error(NO_INTERNET)
                else -> result.value = Result.Error(UNKNOWN_ERROR)
            }
        }
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterStarshipsUseCase(url).collect{ starships ->
                result.value = Success(starships.map { it.toPresentation() })
            }
        }
        return result
    }

    private fun getCharacterVehicles(url: String): LiveData<Result<List<VehiclePresentation>>>{
        val result = MutableLiveData<Result<List<VehiclePresentation>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            when (throwable){
                is UnknownHostException -> result.value = Result.Error(NO_INTERNET)
                else -> result.value = Result.Error(UNKNOWN_ERROR)
            }
        }
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterVehiclesUseCase(url).collect{ vehicles ->
                result.value = Success(vehicles.map { it.toPresentation() })
            }
        }
        return result
    }

    fun deleteCharacter(url: String){
        viewModelScope.launch {
            deleteCharacterUseCase(url)
        }
    }
}