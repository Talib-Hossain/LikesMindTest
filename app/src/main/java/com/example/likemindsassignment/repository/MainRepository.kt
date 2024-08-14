package com.example.likemindsassignment.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.likemindsassignment.api.HarryPoterApi
import com.example.likemindsassignment.data.HarryPoterResponse
import com.example.likemindsassignment.utils.NetworkResult
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val api: HarryPoterApi
) {

    private val _harryResponseLiveData = MutableLiveData<NetworkResult<HarryPoterResponse>>()
    val harryResponseLiveData: LiveData<NetworkResult<HarryPoterResponse>>
        get() = _harryResponseLiveData

    suspend fun getHarryPoterList() {
        _harryResponseLiveData.postValue(NetworkResult.Loading())
        val response = api.getHarryPoterItem()
        if(response.isSuccessful && response.body() != null){
            _harryResponseLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else{
            _harryResponseLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
    }
}