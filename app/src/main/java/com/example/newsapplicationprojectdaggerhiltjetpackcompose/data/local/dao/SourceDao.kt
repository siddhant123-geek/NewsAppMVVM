package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Source
import kotlinx.coroutines.flow.Flow


@Dao
interface SourceDao {
    @Query("SELECT * FROM source")
    fun getAllSources(): Flow<List<Source>>

    @Insert
    fun insertAll(sources: List<Source>)

    @Query("DELETE FROM source")
    fun deleteAll()

    @Transaction
    fun deleteAndInsertAllSources(sources: List<Source>) {
        deleteAll()
        insertAll(sources)
    }
}