package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiArticle
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.TopHeadlineRepository
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.UiState
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.COUNTRY
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    networkHelper: NetworkHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        if (networkHelper.isNetworkConnected()) {
            fetchNews()
        } else {
            fetchNewsDirectlyFromDB()
        }
    }

    private fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun fetchNewsDirectlyFromDB() {
        viewModelScope.launch {
            topHeadlineRepository.getArticlesDirectlyFromDB()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}