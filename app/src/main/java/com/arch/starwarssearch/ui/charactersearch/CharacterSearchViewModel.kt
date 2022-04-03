package com.arch.starwarssearch.ui.charactersearch

import androidx.lifecycle.*
import com.arch.starwarssearch.model.Character
import com.arch.starwarssearch.usecases.SearchCharacterUseCase
import com.arch.starwarssearch.util.AbsentLiveData
import com.arch.starwarssearch.util.Result
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
        if (query.isBlank()){
            AbsentLiveData.create()
        } else {
            searchCharacter(query)
        }
    }
    val characters: LiveData<Result<List<Character>>> get() = _characters

    fun setSearchQuery(query: String){
        val name = query.lowercase().trim()
        if (name == _characterName.value){
            return
        }
        _characterName.value = name
    }
    private fun searchCharacter(characterName: String): LiveData<Result<List<Character>>>{
        val result = MutableLiveData<Result<List<Character>>>()
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            when (throwable){
                is UnknownHostException -> result.value = Result.Error("No internet connection")
                else -> result.value = Result.Error("Unknown error")
            }
        }
        job?.cancel()
        result.value = Result.Loading
        job = viewModelScope.launch(coroutineExceptionHandler) {
            searchCharacterUseCase(characterName).collect {
                result.value = Result.Success(it)
            }
        }
        return result
    }
}