package com.example.githubapi.ui.view.token

data class AccessTokenRequestParam(
    val deviceCode: String,
    val interval: Long,
    val expireIn: Long
)
