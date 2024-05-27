package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.country

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountriesRoute(onItemClick: (String) -> Unit) {
    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            CountriesScreen(onItemClick)
        }
    })
}

@Composable
fun CountriesScreen(onItemClick: (String) -> Unit) {
    val countries = listOf(
        "United Arab Emirates",
        "Argentina",
        "Austria",
        "Australia",
        "Belgium",
        "Bulgaria",
        "Brazil",
        "United States",
        "Canada",
        "Switzerland",
        "China",
        "Colombia",
        "Cuba",
        "Germany",
        "Egypt"
    )

    CreateHeading(nameOfTheScreen = AppConstants.COUNTRIES)
    LazyColumn {
        items(countries, key = { country -> country }) { country ->
            RenderCountry(country, onItemClick)
        }
    }
}

@Composable
fun RenderCountry(country: String, onItemClick: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Add padding
            .background(Color.Cyan) // Add background color
            .clickable { onItemClick(country) }
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Add border with rounded corners
            .padding(8.dp) // Add padding within the border
    ) {
        Text(
            text = country,
            style = MaterialTheme.typography.titleMedium, // Apply typography style
            color = Color.Black, // Text color
            textAlign = TextAlign.Center, // Center align the text
            modifier = Modifier.fillMaxWidth() // Make the text occupy the entire width
        )
    }
}