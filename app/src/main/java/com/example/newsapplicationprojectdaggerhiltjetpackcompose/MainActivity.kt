package com.example.newsapplicationprojectdaggerhiltjetpackcompose

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.NewsNavHost
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsAppTheme {
                NewsNavHost()
            }
        }
    }
}