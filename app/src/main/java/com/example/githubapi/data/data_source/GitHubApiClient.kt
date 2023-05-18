package com.example.githubapi.data.data_source

import android.util.Log
import com.example.githubapi.Secret
import com.example.githubapi.data.pojo.commit.CommitInfo
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object GitHubApiClient {
    private const val TAG: String = "ApiClient"
    private const val BASE_URL: String = "https://api.github.com"
    private const val OWNER: String = "sin0713"

    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()

    private val service: GitHubApiService =
        retrofit.create(GitHubApiService::class.java)


    fun getCommits(repository: String): Single<List<CommitInfo>>  {
        return Single.create { emiter ->

            val call: Call<List<CommitInfo>> = service.getCommits(
                OWNER, repository)
            kotlin.runCatching { call.execute() }
                .onSuccess { response ->
                    Log.d(TAG, "api request is successful!")
                    emiter.onSuccess(response.body() ?: listOf())
                }
                .onFailure {
                    Log.d(TAG, "getCommits is Failure")
                }
        }
    }
    fun search(token: String, searchWord: String): Single<SearchRepositoryInfo> {
        return Single.create { emitter ->
            val call: Call<SearchRepositoryInfo> = service.search(token, searchWord)
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