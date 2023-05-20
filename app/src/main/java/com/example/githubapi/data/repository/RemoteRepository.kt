package com.example.githubapi.data.repository

import android.util.Log
import com.example.githubapi.data.data_source.GitHubApiService
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import com.example.githubapi.domain.repository.IRemoteRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import javax.inject.Inject

class RemoteRepository @Inject constructor() : IRemoteRepository {
    private val TAG: String = "Remote Repository"
    @Inject lateinit var apiService: GitHubApiService

    override fun searchRepositoryApi(token: String, searchWord: String): Single<SearchRepositoryInfo> {
        return Single.create { emitter ->
            val call: Call<SearchRepositoryInfo> = apiService.search(token, searchWord)
            kotlin.runCatching { call.execute() }
                .onSuccess { response ->
                    Log.d(TAG, "runCatching onSuccess")

                    // errorBodyチェック
                    response.errorBody()?.let {
                        emitter.onError(Throwable(it.string()))
                        return@create
                    }

                    response.body()?.let {
                        emitter.onSuccess(it)
                        return@create
                    }

                    emitter.onError(Throwable())
                }
                .onFailure {
                    Log.d(TAG, "ApiClient#search Failure for ${it.message}")
                    emitter.onError(it)
                }
        }
    }
}