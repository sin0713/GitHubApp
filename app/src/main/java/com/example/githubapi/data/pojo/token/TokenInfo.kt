package com.example.githubapi.data.pojo.token

import com.squareup.moshi.Json

data class TokenInfo(
    @Json(name = "access_token")
    val accessToken: String = "",
    @Json(name = "token_type")
    val tokenType: String = "",
    val scope: String = "",
    @Json(name = "error")
    val error: String = "",
    @Json(name = "error_description")
    val errorDescription: String = ""
)
