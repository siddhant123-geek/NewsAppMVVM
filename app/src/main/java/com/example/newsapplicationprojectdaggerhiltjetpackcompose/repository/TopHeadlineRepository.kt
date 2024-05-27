package com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository

import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.api.NetworkService
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.MyAppDataBaseService
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiArticle
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.toArticleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TopHeadlineRepository @Inject constructor(
    private val networkService: NetworkService,
    private val dataBaseService: MyAppDataBaseService
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTopHeadlines(country: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadlines(country)) }
            .flowOn(Dispatchers.IO)
            .map {
                it.articles.map { apiArticle -> apiArticle.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(dataBaseService.deleteAndInsertAllArticles((articles))) }
            }.flatMapConcat {
                dataBaseService.getArticles()
            }
    }

    fun getArticlesDirectlyFromDB(): Flow<List<Article>> {
        return dataBaseService.getArticles()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTopHeadlinesByLanguage(language: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadLinesByLanguage(language)) }
            .flowOn(Dispatchers.IO)
            .map {
                it.articles.map { apiArticle -> apiArticle.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(dataBaseService.deleteAndInsertAllArticles((articles))) }
            }.flatMapConcat {
                dataBaseService.getArticles()
            }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun getTopHeadlinesByKeyword(keyword: String): Flow<List<Article>> {
        return flow { emit(networkService.getTopHeadlinesByKeyword(keyword)) }
            .flowOn(Dispatchers.IO)
            .map {
                it.articles.map { apiArticle -> apiArticle.toArticleEntity() }
            }.flatMapConcat { articles ->
                flow { emit(dataBaseService.deleteAndInsertAllArticles((articles))) }
            }.flatMapConcat {
                dataBaseService.getArticles()
            }
    }
}