package com.example.githubapi.data.pojo

data class RepositoryCard(
    val name: String,
    val author: String,
    val createdAt: String,
    val stars: Int,
    val watchers: Int,
    val forks: Int
)
