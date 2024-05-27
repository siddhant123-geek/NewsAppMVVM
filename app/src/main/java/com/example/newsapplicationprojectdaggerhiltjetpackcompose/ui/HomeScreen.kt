package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.HomeItem
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.Route
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.COUNTRIES
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.LANGUAGES
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.SEARCH
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.SOURCES
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.TOP_HEADLINES

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenRoute(
    onItemClick: (url: Route) -> Unit
) {
    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            HomeScreen(onItemClick)
        }
    })

}

@Composable
fun HomeScreen(onItemClick: (url: Route) -> Unit) {
    val homeScreenList = ArrayList<HomeItem>()
    homeScreenList.add(HomeItem(1, Route.TopHeadline, TOP_HEADLINES))
    homeScreenList.add(HomeItem(2, Route.NewsSource, SOURCES))
    homeScreenList.add(HomeItem(3, Route.Countries, COUNTRIES))
    homeScreenList.add(HomeItem(4, Route.Language, LANGUAGES))
    homeScreenList.add(HomeItem(5, Route.Search, SEARCH))

    LazyColumn {
        items(homeScreenList, key = { homeItem -> homeItem.id }) { homeItem ->
            HomeItemComposable(homeItem, onItemClick)
        }
    }
}

@Composable
fun HomeItemComposable(homeItem: HomeItem, onClick: (Route) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp) // Add padding
            .background(Color.Cyan) // Add background color
            .clickable { onClick(homeItem.route) }
            .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)) // Add border with rounded corners
            .padding(8.dp) // Add padding within the border
    ) {
        Text(
            text = homeItem.name,
            style = MaterialTheme.typography.titleMedium, // Apply typography style
            color = Color.Black, // Text color
            textAlign = TextAlign.Center, // Center align the text
            modifier = Modifier.fillMaxWidth() // Make the text occupy the entire width
        )
    }
}