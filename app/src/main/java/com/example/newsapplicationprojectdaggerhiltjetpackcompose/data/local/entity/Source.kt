package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "source")
data class Source(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "sourceId")
    val id: Int = 0,
    @ColumnInfo(name = "sourceUrl")
    val url: String? = "",
    @ColumnInfo(name = "sourceName")
    val name: String = "",
)