package com.example.likemindsassignment.api

import com.example.likemindsassignment.data.HarryPoterResponse
import com.example.likemindsassignment.data.HarryPoterResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface HarryPoterApi {

    @GET("/en/characters")
    suspend fun getHarryPoterItem(): Response<HarryPoterResponse>

    @GET("/en/characters")
    suspend fun getHarryPoterCharacter(
        @Query("index") index: Int
    ): Response<HarryPoterResponseItem>
}