package com.example.githubapi.ui.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.githubapi.domain.impl.SearchRepositoryImpl
import com.example.githubapi.domain.use_case.SearchRepositoryUseCase
import com.example.githubapi.ui.HomeUiState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import java.util.Timer
import java.util.concurrent.TimeUnit
import kotlin.concurrent.scheduleAtFixedRate

class HomeViewModel : ViewModel() {
    private val useCase: SearchRepositoryUseCase = SearchRepositoryImpl()

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
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

    fun updateDialogState(shouldShow: Boolean) {
        _homeUiState.update {
            it.copy(showDialog = shouldShow)
        }
    }
}