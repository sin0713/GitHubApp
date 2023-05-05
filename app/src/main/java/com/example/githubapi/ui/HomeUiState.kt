package com.example.githubapi.ui

import com.example.githubapi.data.pojo.RepositoryCard

data class HomeUiState (
    val errorMessage: String = "",
    val isLoading: Boolean = false,
    val data: List<RepositoryCard> = emptyList()
)







