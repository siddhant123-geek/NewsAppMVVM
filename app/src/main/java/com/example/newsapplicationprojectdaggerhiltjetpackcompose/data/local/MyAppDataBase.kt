package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.dao.ArticleDao
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.dao.SourceDao
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Source

@Database(entities = [Article::class, Source::class], version = 1, exportSchema = false)
abstract class MyAppDataBase: RoomDatabase() {

    abstract fun articleDao(): ArticleDao

    abstract fun sourceDao(): SourceDao
}