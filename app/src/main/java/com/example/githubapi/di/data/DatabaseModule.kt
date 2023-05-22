package com.example.githubapi.di.data

import android.content.Context
import com.example.githubapi.data.dao.VerificationInfoDao
import com.example.githubapi.data.db.GitHubApiAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext appContext: Context
    ): GitHubApiAppDatabase {
        return GitHubApiAppDatabase.getDatabase(appContext)
    }

    @Provides
    fun provideVerificaitonInfoDao(
        database: GitHubApiAppDatabase
    ): VerificationInfoDao {
        return database.verificationInfoDao()
    }
}
