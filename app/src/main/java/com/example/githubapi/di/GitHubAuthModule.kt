package com.example.githubapi.di

import com.example.githubapi.data.data_source.GitHubApiService
import com.example.githubapi.data.data_source.GitHubService
import com.example.githubapi.di.annotation.GithubAuthRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object GitHubAuthModule {

    @Provides
    @Singleton
    fun privedeGitHubAuthSearvice(
        @GithubAuthRetrofit retrofit: Retrofit
    ): GitHubService {
        return retrofit.create(GitHubService::class.java)
    }
}
