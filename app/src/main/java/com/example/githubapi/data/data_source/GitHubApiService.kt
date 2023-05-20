package com.example.githubapi.data.data_source

import com.example.githubapi.data.pojo.commit.CommitInfo
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("/repos/{owner}/{repository}/commits")
    fun getCommits(
        @Path("owner") owner: String,
        @Path("repository") repository: String
    ): Call<List<CommitInfo>>

    @GET("/search/repositories")
    fun search(
        @Header("Authorization") token: String,
        @Query("q") searchWord: String
    ): Call<SearchRepositoryInfo>
}