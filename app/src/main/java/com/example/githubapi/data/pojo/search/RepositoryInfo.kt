package com.example.githubapi.data.pojo.search

import com.squareup.moshi.Json


data class RepositoryInfo(
    @Json(name = "full_name")
    val fullName: String,
    val owner: Owner,
    @Json(name = "created_at")
    val createdAt: String,
    @Json(name = "stargazers_count")
    val stargazersCount: Int,
    @Json(name = "watchers_count")
    val watchersCount: Int,
    @Json(name = "forks_count")
    val forksCount: Int,
)
