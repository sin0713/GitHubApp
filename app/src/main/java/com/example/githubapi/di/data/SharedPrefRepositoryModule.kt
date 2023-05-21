package com.example.githubapi.di.data

import com.example.githubapi.data.repository.SharedPrefRepository
import com.example.githubapi.domain.repository.ISharedPrefRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SharedPrefRepositoryModule {

    @Binds
    abstract fun bindSharedPrefRepository(
        sharedPrefRepositoryImpl: SharedPrefRepository
    ): ISharedPrefRepository
}
