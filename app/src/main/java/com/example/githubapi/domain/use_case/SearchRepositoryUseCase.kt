package com.example.githubapi.domain.use_case

import com.example.githubapi.ui.HomeUiState

interface SearchRepositoryUseCase {
    fun handle(
        token: String,
        searchWord: String,
        onComplete: (HomeUiState) -> Unit
    )
}