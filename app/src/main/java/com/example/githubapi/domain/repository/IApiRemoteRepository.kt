package com.example.githubapi.domain.repository

import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import io.reactivex.rxjava3.core.Observable

interface IApiRemoteRepository {
    fun searchRepositoryApi(token: String, searchWord: String): Observable<SearchRepositoryInfo>
}