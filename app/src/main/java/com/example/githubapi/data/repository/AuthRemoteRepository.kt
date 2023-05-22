package com.example.githubapi.data.repository

import android.util.Log
import com.example.githubapi.data.data_source.GitHubService
import com.example.githubapi.data.pojo.token.VerificationInfo
import com.example.githubapi.domain.repository.IAuthRemoteRepository
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import javax.inject.Inject

class AuthRemoteRepository @Inject constructor() : IAuthRemoteRepository {
    companion object {
        private const val TAG: String = "GitHubClient"

        // request info
        private const val CLIENT_ID: String = "4c9bd532c1c1b9433c64"
        private const val GRANT_TYPE: String = "urn:ietf:params:oauth:grant-type:device_code"
    }

    @Inject lateinit var authService: GitHubService

    override fun getVerificationInfo(): Single<VerificationInfo> {
        return Single.create { emitter ->
            val call: Call<VerificationInfo> =
                authService.getVerificationInfo(
                    clientId = CLIENT_ID
                )
            kotlin.runCatching { call.execute() }
                .onSuccess { response ->
                    Log.d(TAG, "api request is successful!")

                    response.body()?.let {
                        emitter.onSuccess(it)
                    }
                }
                .onFailure {
                    Log.d(TAG, "api request is failure")
                }
        }
    }
}