package com.example.githubapi.domain.repository

import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import io.reactivex.rxjava3.core.Single

interface IRemoteRepository {
    fun searchRepositoryApi(searchWord: String): Single<SearchRepositoryInfo>
}