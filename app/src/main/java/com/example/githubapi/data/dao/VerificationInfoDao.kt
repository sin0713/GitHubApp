package com.example.githubapi.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.githubapi.data.entity.VerificationInfo

@Dao
interface VerificationInfoDao {
    @Insert
    fun insert(verificationInfo: VerificationInfo)

    @Query("SELECT * FROM verification_info ORDER BY id DESC LIMIT 1;")
    fun getLatest(): VerificationInfo

    @Query("DELETE FROM verification_info")
    fun clear()
}
