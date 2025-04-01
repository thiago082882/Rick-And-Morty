package org.kmp.rickandmorty.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.data.repository.RickAndMortyRepository
import org.kmp.rickandmorty.utils.Resource
import org.kmp.rickandmorty.utils.categoryList


class RickAndMortyViewModel(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {

    private val _charactersStateFlow =
        MutableStateFlow<Resource<List<Character>>>(Resource.Loading)
    val charactersStateFlow: StateFlow<Resource<List<Character>>>
        get() = _charactersStateFlow

    var selectedCategories by mutableStateOf(setOf<String>())

    init {
        fetchCharacters()
    }

//    fun fetchCharacters() {
//        viewModelScope.launch(Dispatchers.IO) {
//            _charactersStateFlow.emit(Resource.Loading)
//            try {
//                val characters = rickAndMortyRepository.getAllCharacters()
//                _charactersStateFlow.emit(Resource.Success(characters))
//            } catch (e: Exception) {
//                _charactersStateFlow.emit(Resource.Error(e.message.toString()))
//            }
//        }
//    }

    fun fetchCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            _charactersStateFlow.emit(Resource.Loading)
            try {
                val characters = rickAndMortyRepository.getAllCharacters()
                val filteredCharacters = if (selectedCategories.isEmpty()) {
                    characters
                } else {
                    characters.filter { selectedCategories.contains(it.status.lowercase()) }
                }

                _charactersStateFlow.emit(Resource.Success(filteredCharacters))
            } catch (e: Exception) {
                _charactersStateFlow.emit(Resource.Error(e.message.toString()))
            }
        }
    }

    fun toggleCategory(category: String) {
        selectedCategories = if (category in selectedCategories) {
            selectedCategories - category
        } else {
            selectedCategories + category
        }
        fetchCharacters()
    }
}
