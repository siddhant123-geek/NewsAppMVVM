package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local

import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Source
import kotlinx.coroutines.flow.Flow

class MyAppDataBaseService constructor(private val appDataBase: MyAppDataBase): DataBaseService {
    override fun getArticles(): Flow<List<Article>> {
        return appDataBase.articleDao().getAllArticles()
    }

    override fun deleteAndInsertAllArticles(articles: List<Article>) {
        appDataBase.articleDao().deleteAndInsertAllArticles(articles)
    }

    override fun getSources(): Flow<List<Source>> {
        return appDataBase.sourceDao().getAllSources()
    }

    override fun deleteAndInsertAllSources(sources: List<Source>) {
        appDataBase.sourceDao().deleteAndInsertAllSources(sources)
    }
}