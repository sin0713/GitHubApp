package com.example.githubapi.data.data_source

import com.example.githubapi.data.pojo.token.TokenInfo
import com.example.githubapi.data.pojo.token.VerificationInfo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHubService {
    @POST("/login/device/code")
    fun getVerificationInfo(
        @Header("Accept") contentType: String,
        @Query("client_id") clientId: String
    ): Call<VerificationInfo>

    @POST("/login/oauth/access_token")
    fun getAccessToken(
        @Header("Accept") contentType: String,
        @Query("client_id") clientId: String,
        @Query("device_code") deviceCode: String,
        @Query("grant_type") grantType: String,
    ): Call<TokenInfo>
    @POST("/login/oauth/access_token")
    fun getAccessToken2(
        @Header("Accept") contentType: String,
        @Query("client_id") clientId: String,
        @Query("device_code") deviceCode: String,
        @Query("grant_type") grantType: String,
    ): Call<ResponseBody>
}