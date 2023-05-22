package com.example.githubapi.data.repository

import com.example.githubapi.data.data_source.GitHubApiService
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import com.example.githubapi.domain.repository.IApiRemoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class RemoteRepository @Inject constructor() : IApiRemoteRepository {
    companion object {
        private const val TAG: String = "Remote Repository"
    }

    @Inject lateinit var apiService: GitHubApiService

    override fun searchRepositoryApi(token: String, searchWord: String): Observable<SearchRepositoryInfo> {
        return apiService.search(token, searchWord)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}