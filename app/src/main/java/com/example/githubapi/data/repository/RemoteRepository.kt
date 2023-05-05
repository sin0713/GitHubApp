package com.example.githubapi.data.repository

import com.example.githubapi.data.data_source.ApiClient
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import com.example.githubapi.domain.repository.IRemoteRepository
import io.reactivex.rxjava3.core.Single

class RemoteRepository : IRemoteRepository {
    override fun searchRepositoryApi(searchWord: String): Single<SearchRepositoryInfo> {
        return ApiClient.search(searchWord)
    }
}