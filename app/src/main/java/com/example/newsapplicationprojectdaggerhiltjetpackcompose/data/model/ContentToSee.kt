package com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model

import com.google.gson.annotations.SerializedName

data class ContentToSee(
    @SerializedName("text") val text: String
)
