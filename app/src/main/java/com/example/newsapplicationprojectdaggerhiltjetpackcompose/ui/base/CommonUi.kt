package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.R

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val contentDesc = stringResource(R.string.loading)
        CircularProgressIndicator(modifier = Modifier
            .align(Alignment.Center)
            .semantics {
                contentDescription = contentDesc
            })
    }
}

@Composable
fun ShowError(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Red,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(4.dp)
        )
    }
}

@Composable
fun CreateHeading(nameOfTheScreen: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth() // Make the Box occupy the entire width
            .padding(vertical = 16.dp) // Add vertical padding
            .background(color = Color.Green) // Set the background color
    ) {
        Text(
            text = nameOfTheScreen,
            style = MaterialTheme.typography.titleLarge, // Apply a typography style for heading
            color = MaterialTheme.colorScheme.primary, // Use primary color for heading text
            textAlign = TextAlign.Center, // Center align the text
            modifier = Modifier
                .fillMaxWidth() // Make the text occupy the entire width of the Box
                .padding(horizontal = 16.dp) // Add horizontal padding to the text
        )
    }
}