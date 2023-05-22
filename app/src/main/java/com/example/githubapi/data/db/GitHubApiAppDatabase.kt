package com.example.githubapi.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubapi.data.dao.VerificationInfoDao

@Database(entities = [], version = 0)
abstract class GitHubApiAppDatabase : RoomDatabase() {
    abstract fun verificationInfoDao(): VerificationInfoDao

    companion object {
        @Volatile
        private var INSTANCE: GitHubApiAppDatabase? = null

        fun getDatabase(
            context: Context
        ) : GitHubApiAppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    GitHubApiAppDatabase::class.java,
                    "github_api_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return@synchronized instance
            }
        }
    }
}