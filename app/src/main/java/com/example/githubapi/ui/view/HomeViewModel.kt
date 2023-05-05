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

    private var _cardData: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val cardData: StateFlow<HomeUiState>
        get() = _cardData

    fun searchRepository(searchWord: String) {
        _cardData.update {
            it.copy(isLoading = true)
        }

        useCase.handle(searchWord) { uiState ->
            _cardData.value = uiState
        }
    }
}