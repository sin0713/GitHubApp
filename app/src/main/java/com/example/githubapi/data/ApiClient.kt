package com.example.githubapi.data

import android.util.Log
import com.example.githubapi.Secret
import com.example.githubapi.data.pojo.CommitInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.rxjava3.core.Single
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiClient {
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

    private val service: GitHubService =
        retrofit.create(GitHubService::class.java)


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
    fun search(): Single<String> {
        return Single.create { emitter ->
            val call: Call<ResponseBody> = service.search(Secret.TOKEN, "bookers")
            kotlin.runCatching { call.execute() }
                .onSuccess { response ->
                    Log.d(TAG, "api request is successful!")
                    println(response.errorBody())
                    emitter.onSuccess(response.body()?.string() ?: "")
                }
                .onFailure {
                    Log.d(TAG, "getCommits is Failure")
                }
        }
    }

}