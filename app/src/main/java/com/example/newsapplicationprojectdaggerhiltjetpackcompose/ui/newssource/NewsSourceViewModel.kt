package com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.data.local.entity.Source
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.repository.NewsSourcesRepository
import com.example.newsapplicationprojectdaggerhiltjetpackcompose.ui.base.UiState
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
class NewsSourceViewModel @Inject constructor(
    private val newsSourcesRepository: NewsSourcesRepository,
    networkHelper: NetworkHelper
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Source>>> = _uiState

    init {
        if (networkHelper.isNetworkConnected()) {
            fetchNewsSources()
        } else {
            fetchSourcesDirectlyFromDB()
        }
    }

    private fun fetchNewsSources() {
        viewModelScope.launch {
            newsSourcesRepository.getNewsSources()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun fetchSourcesDirectlyFromDB() {
        viewModelScope.launch {
            newsSourcesRepository.getSourcesDirectlyFromDB()
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}