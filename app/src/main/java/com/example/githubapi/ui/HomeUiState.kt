package com.example.githubapi.ui

import com.example.githubapi.data.pojo.RepositoryCard

data class HomeUiState (
    val searchWord: String = "",
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val data: List<RepositoryCard> = emptyList()
)







