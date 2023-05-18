package com.example.githubapi.domain.repository

import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import io.reactivex.rxjava3.core.Single

interface IRemoteRepository {
    fun searchRepositoryApi(token: String, searchWord: String): Single<SearchRepositoryInfo>
}