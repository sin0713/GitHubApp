package com.example.githubapi.ui

import com.example.githubapi.domain.model.RepositoryModel

data class HomeUiState (
    val searchWord: String = "",
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val showGetTokenDialog: Boolean = false,
    val data: List<RepositoryModel> = emptyList()
)







