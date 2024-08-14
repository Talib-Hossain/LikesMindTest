package com.example.likemindsassignment.di

import com.example.likemindsassignment.utils.Constant.BASE_URL
import com.example.likemindsassignment.api.HarryPoterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
    }

    @Singleton
    @Provides
    fun providesHarryPoterAPI(retrofitBuilder: Retrofit.Builder): HarryPoterApi {
        return retrofitBuilder.build().create(HarryPoterApi::class.java)
    }
}