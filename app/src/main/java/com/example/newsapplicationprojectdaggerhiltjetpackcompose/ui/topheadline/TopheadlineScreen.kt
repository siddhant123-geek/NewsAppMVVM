package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Source
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiArticle
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiSource
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.ShowError
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.ShowLoading
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.UiState
import org.tinylog.Logger

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    viewModel: TopHeadlineViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(content = { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineScreen(uiState, onNewsClick)
        }
    })

}

@Composable
fun TopHeadlineScreen(uiState: UiState<List<Article>>, onNewsClick: (url: String) -> Unit) {
    when (uiState) {
        is UiState.Success -> {
            Logger.debug("Coming inside success inside TopHeadlineScreen", null)
            ArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            Logger.debug("Coming inside loading inside TopHeadlineScreen", null)
            ShowLoading()
        }

        is UiState.Error -> {
            Logger.error("Coming inside error inside TopHeadlineScreen with error message" +
                    " ${uiState.message}",
                    null)
            ShowError(uiState.message)
        }
    }
}

@Composable
fun ArticleList(articles: List<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles, key = { article -> article.id }) { article ->
            Article(article, onNewsClick)
        }
    }
}

@Composable
fun Article(article: Article, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (!article.url.isNullOrEmpty()) {
                onNewsClick(article.url)
            }
        }) {
        BannerImage(article)
        TitleText(article.title)
        DescriptionText(article.description)
        SourceText(article.source)
    }

}

@Composable
fun BannerImage(article: Article) {
    AsyncImage(
        model = article.imageUrl,
        contentDescription = article.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}

@Composable
fun TitleText(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun DescriptionText(description: String?) {
    if (!description.isNullOrEmpty()) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun SourceText(source: Source) {
    Text(
        text = source.name,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        maxLines = 1,
        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
    )
}