package com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository

import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.api.NetworkService
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NewsSourcesRepository @Inject constructor(private val networkService: NetworkService) {

    fun getNewsSources(): Flow<List<ApiSource>> {
        return flow {
            emit(networkService.getNewsSource())
        }.map {it.sources}
    }
}