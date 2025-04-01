package org.kmp.rickandmorty.ui.favorite


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import org.kmp.rickandmorty.data.model.Character
import org.kmp.rickandmorty.data.repository.LocalCharacterRepository
import org.kmp.rickandmorty.utils.Resource


class FavoriteViewModel(
    private val localCharacterRepository: LocalCharacterRepository
) : ViewModel() {

    private val _bookmarkNewsStateFlow =
        MutableStateFlow<Resource<List<Character>>>(Resource.Loading)
    val bookmarkNewsStateFlow: StateFlow<Resource<List<Character>>>
        get() = _bookmarkNewsStateFlow

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            _bookmarkNewsStateFlow.emit(Resource.Loading)
           localCharacterRepository.getCharacters().catch {
                it.printStackTrace()
                _bookmarkNewsStateFlow.emit(Resource.Error(it.message.toString()))
            }.collect { result ->
                _bookmarkNewsStateFlow.emit(Resource.Success(result))
            }
        }
    }
}