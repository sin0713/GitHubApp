package com.example.githubapi.data.pojo

import com.example.githubapi.domain.model.RepositoryModel

object SampleData {
    val repositoryCardInfo: RepositoryModel =
        RepositoryModel(
            name = "AndroidApp",
            author = "shin0713",
            createdAt = "2022/04/23",
            stars = 300,
            forks = 300,
            watchers = 300
        )
}