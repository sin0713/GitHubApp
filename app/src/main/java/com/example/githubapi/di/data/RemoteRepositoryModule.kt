package com.example.githubapi.di.data

import com.example.githubapi.data.repository.RemoteRepository
import com.example.githubapi.domain.repository.IRemoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteRepositoryModule {

    @Binds
    abstract fun bindRemoteRepository(
        remoteRepositoryImpl: RemoteRepository
    ): IRemoteRepository
}
