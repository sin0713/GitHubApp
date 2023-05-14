package com.example.githubapi.ui.view.token

data class TokenUiState(
    val authenticationUrl: String = "",
    val userCode: String = "",
    val isLoading: Boolean = true
)
