package com.example.githubapi

import android.util.Log
import com.example.githubapi.data.pojo.CommitInfo
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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


    fun getCommits(repository: String)  {
        val call: Call<List<CommitInfo>> = service.getCommits(OWNER, repository)
        kotlin.runCatching { call.execute() }
            .onSuccess { response ->
                response.body()?.forEach { commit ->
                    Log.d(TAG, commit.sha)
                }
            }
            .onFailure {
                Log.d(TAG, "getCommits is Failure")
            }
    }

}