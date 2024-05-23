package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model

import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("sources")
    val sources: List<ApiSource> = ArrayList()
)
