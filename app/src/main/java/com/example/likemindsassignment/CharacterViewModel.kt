package com.example.likemindsassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.likemindsassignment.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val repository: CharacterRepository
): ViewModel() {
    val charHarryPoterLiveData get() = repository.charResponseLiveData

    fun getChar(index: Int){
        viewModelScope.launch {
            repository.getCharData(index)
        }
    }
}