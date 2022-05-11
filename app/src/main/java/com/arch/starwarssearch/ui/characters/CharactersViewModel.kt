package com.arch.starwarssearch.ui.characters

import androidx.lifecycle.*
import com.arch.starwarssearch.mapper.toPresentation
import com.arch.starwarssearch.model.CharacterPresentation
import com.arch.starwarssearch.usecases.DeleteAllCharactersUseCase
import com.arch.starwarssearch.usecases.GetAllCharactersUseCase
import com.arch.starwarssearch.util.Result
import com.arch.starwarssearch.util.Result.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val deleteAllCharactersUseCase: DeleteAllCharactersUseCase
): ViewModel() {
    fun getAllCharacters(): LiveData<Result<List<CharacterPresentation>>>{
        val result = getAllCharactersUseCase().asLiveData()
        val characters = result.map {
            it.map { char ->
                char.toPresentation()
            }
        }
        return characters.map { Success(it) }
    }

    fun deleteAllCharacters(){
        viewModelScope.launch {
            deleteAllCharactersUseCase()
        }
    }
}