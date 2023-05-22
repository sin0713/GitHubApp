package com.example.githubapi.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "verification_info")
data class VerificationInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val userCode: String,
    val deviceCode: String,
    val interval: Long,
    val verificationUrl: String,
    val expiredAt: Long
)