package com.example.likemindsassignment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.likemindsassignment.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MainRepository
) : ViewModel() {
    val harryPoterLiveData get() = repository.harryResponseLiveData

    fun getHarryResponse(){
        viewModelScope.launch {
            repository.getHarryPoterList()
        }
    }
}