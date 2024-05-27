package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.language

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.CreateHeading
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants
@Composable
fun LanguagesRoute(onItemClick: (String) -> Unit) {
    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            LanguagesScreen(onItemClick)
        }
    })
}

@Composable
fun LanguagesScreen(onItemClick: (String) -> Unit) {
    val languages = listOf(
        "Arabic",
        "German",
        "English",
        "Spanish",
        "French",
        "Hebrew",
        "Italian",
        "Dutch",
        "Norwegian",
        "Portuguese",
        "Russian",
        "Swedish",
        "Urdu",
        "Chinese"
    )

    CreateHeading(nameOfTheScreen = AppConstants.LANGUAGES)
    LazyColumn {
        items(languages, key = { language -> language }) { language ->
            RenderLanguage(language, onItemClick)
        }
    }
}

@Composable
fun RenderLanguage(language: String, onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Add padding
            .background(Color.Cyan) // Add background color
            .clickable { onItemClick(language) }
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Add border with rounded corners
            .padding(8.dp) // Add padding within the border
    ) {
        Text(
            text = language,
            style = MaterialTheme.typography.titleMedium, // Apply typography style
            color = Color.Black, // Text color
            textAlign = TextAlign.Center, // Center align the text
            modifier = Modifier.fillMaxWidth() // Make the text occupy the entire width
        )
    }
}