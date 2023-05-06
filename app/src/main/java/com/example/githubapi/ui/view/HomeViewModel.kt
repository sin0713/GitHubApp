package com.example.githubapi.ui.view

import androidx.lifecycle.ViewModel
import com.example.githubapi.domain.impl.SearchRepositoryImpl
import com.example.githubapi.domain.use_case.SearchRepositoryUseCase
import com.example.githubapi.ui.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {
    private val useCase: SearchRepositoryUseCase = SearchRepositoryImpl()

    private var _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiSate: StateFlow<HomeUiState>
        get() = _homeUiState

    fun searchRepository() {
        _homeUiState.update {
            it.copy(isLoading = true)
        }

        useCase.handle(_homeUiState.value.searchWord) { uiState ->
            _homeUiState.value = uiState
        }
    }

    fun updateSearchWord(searchWord: String) {
        _homeUiState.update {
            it.copy(searchWord = searchWord)
        }
    }
}