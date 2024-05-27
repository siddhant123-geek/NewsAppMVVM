package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.instantsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Article
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiArticle
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.TopHeadlineRepository
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.UiState
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.DEBOUNCE_TIMEOUT
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.MIN_SEARCH_CHAR
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.NetworkHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InstantSearchViewModel @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository,
                                                 networkHelper: NetworkHelper) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    private val _query = MutableStateFlow("")
    val query = _query

    init {
        if(networkHelper.isNetworkConnected()) {
            createNewsFlow()
        }
    }

    fun searchNews(searchQuery: String) {
        _query.value = searchQuery
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun createNewsFlow() {
        viewModelScope.launch {
            _query.debounce(DEBOUNCE_TIMEOUT)
                .filter {
                    if (it.isNotEmpty() && it.length >= MIN_SEARCH_CHAR) {
                        return@filter true
                    } else {
                        _uiState.value = UiState.Success(emptyList())
                        return@filter false
                    }
                }
                .distinctUntilChanged()
                .flatMapLatest {
                    _uiState.value = UiState.Loading
                    return@flatMapLatest topHeadlineRepository.getTopHeadlinesByKeyword(it)
                        .catch { e ->
                            // handle error properly
                            _uiState.value = UiState.Error(e.toString())
                        }
                }
                .flowOn(Dispatchers.IO)
                .collect {
                    // handle response and empty response properly
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}