package com.arch.starwarssearch.ui.charactersearch

import androidx.lifecycle.*
import com.arch.starwarssearch.mapper.toPresentation
import com.arch.starwarssearch.model.CharacterPresentation
import com.arch.starwarssearch.usecases.SearchCharacterUseCase
import com.arch.starwarssearch.util.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class CharacterSearchViewModel @Inject constructor(
    private val searchCharacterUseCase: SearchCharacterUseCase
): ViewModel() {
    private val _characterName = MutableLiveData<String>()
    private var job: Job? = null

    private val _characters = _characterName.switchMap { query ->
        if(query.isBlank()) {
            AbsentLiveData.create()
        } else {
            searchCharacter(query)
        }
    }
    val characters: LiveData<Result<List<CharacterPresentation>>> get() = _characters

    fun setSearchQuery(query: String){
        val name = query.lowercase().trim()
        if (name == _characterName.value){
            return
        }
        _characterName.value = name
    }
    private fun searchCharacter(characterName: String): LiveData<Result<List<CharacterPresentation>>>{
        wrapEspressoIdlingResource {
            val result = MutableLiveData<Result<List<CharacterPresentation>>>()
            val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
                when (throwable) {
                    is UnknownHostException -> result.value = Result.Error(NO_INTERNET)
                    else -> result.value = Result.Error(UNKNOWN_ERROR)
                }
            }
            job?.cancel()
            result.value = Result.Loading
            job = viewModelScope.launch(coroutineExceptionHandler) {
                searchCharacterUseCase(characterName).collect { results ->
                    val characters = results.map { it.toPresentation() }
                    result.value = Result.Success(characters)
                }
            }
            return result
        }
    }
}