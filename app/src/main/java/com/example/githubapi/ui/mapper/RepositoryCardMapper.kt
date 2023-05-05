package com.example.githubapi.ui.mapper

import androidx.compose.runtime.saveable.autoSaver
import com.example.githubapi.data.pojo.RepositoryCard
import com.example.githubapi.data.pojo.search.RepositoryInfo
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo

class RepositoryCardMapper {
    fun execute(
        searchRepositoryInfo: SearchRepositoryInfo
    ): List<RepositoryCard> {
        val items: List<RepositoryInfo> = searchRepositoryInfo.items
        if (items.isEmpty()) return listOf()

        val result: MutableList<RepositoryCard> = mutableListOf()
        items.forEach { repositoryInfo ->
            result.add(
                RepositoryCard(
                    name = repositoryInfo.fullName,
                    author = repositoryInfo.owner.login,
                    createdAt = repositoryInfo.createdAt,
                    stars = repositoryInfo.stargazersCount,
                    watchers = repositoryInfo.watchersCount,
                    forks = repositoryInfo.forksCount
                )
            )
        }

        return result
    }
}