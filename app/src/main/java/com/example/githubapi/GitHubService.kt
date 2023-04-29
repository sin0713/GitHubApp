package com.example.githubapi

import com.example.githubapi.data.pojo.CommitInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {

    @GET("/repos/{owner}/{repository}/commits")
    fun getCommits(
        @Path("owner") owner: String,
        @Path("repository") repository: String
    ): Call<List<CommitInfo>>
}