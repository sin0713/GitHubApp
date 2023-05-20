package com.example.githubapi.di

import com.example.githubapi.data.data_source.GitHubApiService
import com.example.githubapi.di.annotation.GithubApiRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubApiModule {

    @Provides
    @Singleton
    fun privedeGitHubApiSearvice(
        @GithubApiRetrofit retrofit:  Retrofit
    ): GitHubApiService {
        return retrofit.create(GitHubApiService::class.java)
    }
}