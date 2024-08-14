package com.example.likemindsassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.likemindsassignment.api.HarryPoterApi
import com.example.likemindsassignment.data.HarryPoterResponse
import com.example.likemindsassignment.data.HarryPoterResponseItem
import com.example.likemindsassignment.utils.NetworkResult
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: HarryPoterApi
) {
    private val _CharResponseLiveData = MutableLiveData<NetworkResult<HarryPoterResponseItem>>()
    val charResponseLiveData: LiveData<NetworkResult<HarryPoterResponseItem>>
        get() = _CharResponseLiveData

    suspend fun getCharData(index: Int){
        _CharResponseLiveData.postValue(NetworkResult.Loading())
        val response = api.getHarryPoterCharacter(index)

        if(response.isSuccessful && response.body() != null){
            _CharResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else{
            _CharResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}