package com.example.githubapi.ui.view.home

import androidx.lifecycle.ViewModel
import com.example.githubapi.data.data_source.SharedPrefClient
import com.example.githubapi.domain.impl.SearchRepositoryImpl
import com.example.githubapi.domain.use_case.SearchRepositoryUseCase
import com.example.githubapi.ui.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() :  ViewModel() {
    @Inject lateinit var useCase: SearchRepositoryUseCase

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val homeUiSate: StateFlow<HomeUiState>
        get() = _homeUiState

    fun searchRepository() {
        _homeUiState.update {
            it.copy(isLoading = true)
        }

        val token = SharedPrefClient.getStr(SharedPrefClient.TOKEN_KEY)
        if (token.isEmpty()) {
            _homeUiState.update {
                it.copy(isLoading = false)
            }

            return
        }

        useCase.handle(token, _homeUiState.value.searchWord) { uiState ->
            _homeUiState.value = uiState
        }
    }

    fun updateSearchWord(searchWord: String) {
        _homeUiState.update {
            it.copy(searchWord = searchWord)
        }
    }

    fun updateDialogState(shouldShow: Boolean) {
        _homeUiState.update {
            it.copy(showDialog = shouldShow)
        }
    }
}