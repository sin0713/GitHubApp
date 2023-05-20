package com.example.githubapi.domain.mapper

import com.example.githubapi.domain.model.RepositoryModel
import com.example.githubapi.data.pojo.search.RepositoryInfo
import com.example.githubapi.data.pojo.search.SearchRepositoryInfo
import javax.inject.Inject

class RepositoryCardMapper @Inject constructor() {
    fun execute(
        searchRepositoryInfo: SearchRepositoryInfo
    ): List<RepositoryModel> {
        val items: List<RepositoryInfo> = searchRepositoryInfo.items
        if (items.isEmpty()) return listOf()

        val result: MutableList<RepositoryModel> = mutableListOf()
        items.forEach { repositoryInfo ->
            result.add(
                RepositoryModel(
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