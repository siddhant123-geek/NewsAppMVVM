package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model

import com.google.gson.annotations.SerializedName

data class ApiSource (
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("url")
    val url: String = "",
)
