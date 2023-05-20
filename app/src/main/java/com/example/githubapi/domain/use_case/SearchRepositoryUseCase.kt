package com.example.githubapi.domain.use_case

import com.example.githubapi.domain.model.RepositoryModel

interface SearchRepositoryUseCase {
    fun handle(
        token: String,
        searchWord: String,
        onStart: () -> Unit,
        onComplete: (List<RepositoryModel>) -> Unit,
        onError: (String) -> Unit,
    )
}