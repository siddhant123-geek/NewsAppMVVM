package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model

import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import com.google.gson.annotations.SerializedName

data class ApiArticle(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
    @SerializedName("urlToImage")
    val imageUrl: String = "",
    @SerializedName("source")
    val source: ApiSource
)

fun ApiArticle.toArticleEntity(): Article {
    return Article(
        title = title,
        description = description,
        url = url,
        imageUrl = imageUrl,
        source = source.toSourceEntity()
    )
}
