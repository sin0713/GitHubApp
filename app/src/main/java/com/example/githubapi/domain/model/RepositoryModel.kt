package com.example.githubapi.domain.model

data class RepositoryModel(
    val name: String,
    val author: String,
    val createdAt: String,
    val stars: Int,
    val watchers: Int,
    val forks: Int
)
