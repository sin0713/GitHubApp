package com.example.githubapi.data.repository

import com.example.githubapi.data.data_source.GitHubApiClient
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import com.example.githubapi.domain.repository.IRemoteRepository
import io.reactivex.rxjava3.core.Single

class RemoteRepository : IRemoteRepository {
    override fun searchRepositoryApi(token: String, searchWord: String): Single<SearchRepositoryInfo> {
        return GitHubApiClient.search(token, searchWord)
    }
}