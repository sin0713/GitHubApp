package com.example.githubapi.domain.use_case

import com.example.githubapi.domain.model.RepositoryModel

interface ISearchRepositoryUseCase {
    fun handle(
        searchWord: String,
        showDialog: () -> Unit,
        onStart: () -> Unit,
        onComplete: (List<RepositoryModel>) -> Unit,
        onError: (String) -> Unit,
    )
}