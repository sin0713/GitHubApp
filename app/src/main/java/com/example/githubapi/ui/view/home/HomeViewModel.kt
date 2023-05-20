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
        val token = SharedPrefClient.getStr(SharedPrefClient.TOKEN_KEY)
        if (token.isEmpty()) { return }

        useCase.handle(
            token = token,
            searchWord = _homeUiState.value.searchWord,
            onStart = {
                _homeUiState.update { it.copy(isLoading = true) }
            },
            onComplete = { result ->
                 _homeUiState.update {
                     it.copy(
                         errorMessage = "",
                         isLoading = false,
                         data = result,
                     )
                 }
            },
            onError = { errorMessage ->
                // 前回結果の更新していない
                _homeUiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = errorMessage
                    )
                }
            }
        )
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