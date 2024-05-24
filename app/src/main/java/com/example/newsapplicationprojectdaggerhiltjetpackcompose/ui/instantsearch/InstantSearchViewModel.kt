package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.instantsearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.model.ApiArticle
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.TopHeadlineRepository
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.UiState
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.DEBOUNCE_TIMEOUT
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.utils.AppConstants.MIN_SEARCH_CHAR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class InstantSearchViewModel (private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Success(emptyList()))

    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    private val query = MutableStateFlow("")

    init {
        createNewsFlow()
    }

    fun searchNews(searchQuery: String) {
        query.value = searchQuery
    }

    private fun createNewsFlow() {
        viewModelScope.launch {
            query.debounce(DEBOUNCE_TIMEOUT)
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