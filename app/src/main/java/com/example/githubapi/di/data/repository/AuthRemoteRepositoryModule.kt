package com.example.githubapi.di.data.repository

import com.example.githubapi.data.repository.AuthRemoteRepository
import com.example.githubapi.domain.repository.IAuthRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthRemoteRepositoryModule {

    @Binds
    abstract fun bindAuthRemoteRepository(
        authRemoteRepositoryImpl: AuthRemoteRepository
    ): IAuthRemoteRepository
}
