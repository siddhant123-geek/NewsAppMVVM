package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {

    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<Article>>

    @Insert
    fun insertAll(articles: List<Article>)

    @Query("DELETE FROM article")
    fun deleteAll()

    @Transaction
    fun deleteAndInsertAllArticles(articles: List<Article>) {
        deleteAll()
        insertAll(articles)
    }
}