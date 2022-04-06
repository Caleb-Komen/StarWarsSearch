package com.arch.starwarssearch.ui.characterdetail

import androidx.lifecycle.*
import com.arch.starwarssearch.model.*
import com.arch.starwarssearch.usecases.*
import com.arch.starwarssearch.util.NO_INTERNET
import com.arch.starwarssearch.util.Result
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
    private val getCharacterVehiclesUseCase: GetCharacterVehiclesUseCase
): ViewModel() {
    private val characterUrl = MutableLiveData<String>()

    private val _planet = characterUrl.switchMap { getCharacterPlanet(it) }
    val planet: LiveData<Result<Planet>> get() = _planet

    private val _films = characterUrl.switchMap { getCharacterFilms(it) }
    val films: LiveData<Result<List<Film>>> get() = _films

    private val _species = characterUrl.switchMap { getCharacterSpecies(it) }
    val species: LiveData<Result<List<Specie>>> get() = _species

    private val _starships = characterUrl.switchMap { getCharacterStarships(it) }
    val starships: LiveData<Result<List<StarShip>>> get() = _starships

    private val _vehicles = characterUrl.switchMap { getCharacterVehicles(it) }
    val vehicles: LiveData<Result<List<Vehicle>>> get() = _vehicles

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable){
            is UnknownHostException -> Result.Error(NO_INTERNET)
            else -> Result.Error(UNKNOWN_ERROR)
        }
    }

    fun setCharacterUrl(url: String){
        characterUrl.value = url
    }

    private fun getCharacterPlanet(url: String):LiveData<Result<Planet>> {
        val result = MutableLiveData<Result<Planet>>()
        viewModelScope.launch(coroutineExceptionHandler) {
            result.value = Result.Loading
            getCharacterPlanetUseCase(url).collect{ planet ->
                result.value = Result.Success(planet)
            }
        }
        return result
    }

    private fun getCharacterFilms(url: String): LiveData<Result<List<Film>>>{
        val result = MutableLiveData<Result<List<Film>>>()
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterFilmsUseCase(url).collect{ films ->
                result.value = Result.Success(films)
            }
        }
        return result
    }

    private fun getCharacterSpecies(url: String): LiveData<Result<List<Specie>>>{
        val result = MutableLiveData<Result<List<Specie>>>()
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterSpeciesUseCase(url).collect{ species ->
                result.value = Result.Success(species)
            }
        }
        return result
    }

    private fun getCharacterStarships(url: String): LiveData<Result<List<StarShip>>>{
        val result = MutableLiveData<Result<List<StarShip>>>()
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterStarshipsUseCase(url).collect{ starships ->
                result.value = Result.Success(starships)
            }
        }
        return result
    }

    private fun getCharacterVehicles(url: String): LiveData<Result<List<Vehicle>>>{
        val result = MutableLiveData<Result<List<Vehicle>>>()
        result.value = Result.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            getCharacterVehiclesUseCase(url).collect{ vehicles ->
                result.value = Result.Success(vehicles)
            }
        }
        return result
    }
}