package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.api

import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.NewsSourcesResponse
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.TopHeadlineResponse
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkService {

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadlines(@Query("country") country: String): TopHeadlineResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines/sources")
    suspend fun getNewsSource(): NewsSourcesResponse

    @Headers("X-Api-Key: $API_KEY")
    @GET("top-headlines")
    suspend fun getTopHeadLinesByLanguage(@Query("language") language: String): TopHeadlineResponse
}