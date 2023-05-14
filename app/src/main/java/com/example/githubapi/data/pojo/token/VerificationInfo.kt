package com.example.githubapi.data.pojo.token

import com.squareup.moshi.Json

data class VerificationInfo(
    @Json(name = "device_code")
    val deviceCode: String,
    @Json(name = "user_code")
    val userCode: String,
    @Json(name = "verification_uri")
    val verificationUri: String,
    @Json(name = "expires_in")
    val expiresTime: Int,
    val interval: Int
)
