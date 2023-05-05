package com.example.githubapi.domain.use_case

import com.example.githubapi.ui.HomeUiState

interface SearchRepositoryUseCase {
    fun handle(
        searchWord: String,
        onComplete: (HomeUiState) -> Unit
    )
}