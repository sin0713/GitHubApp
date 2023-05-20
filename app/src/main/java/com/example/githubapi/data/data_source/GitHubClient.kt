package com.example.githubapi.data.data_source

import android.util.Log
import com.example.githubapi.data.pojo.token.TokenInfo
import com.example.githubapi.data.pojo.token.VerificationInfo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

object GitHubClient {
    private const val TAG: String = "GitHubClient"

    // request info
    private const val BASE_URL: String = "https://github.com"
    private const val CLIENT_ID: String = "4c9bd532c1c1b9433c64"
    private const val GRANT_TYPE: String = "urn:ietf:params:oauth:grant-type:device_code"

    private val retrofit: Retrofit =
        RetrofitProvider.provideRetrofit(BASE_URL)

    private val service: GitHubService =
        retrofit.create(GitHubService::class.java)

    fun getVerificationInfo(): Single<VerificationInfo> {
        return Single.create { emitter ->
            val call: Call<VerificationInfo> =
                service.getVerificationInfo(
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
                    Log.d(TAG, "getCommits is Failure")
                }
        }
    }

   fun getAccessToken(
       deviceCode: String,
       interval: Long,
       timeOut: Long
   ): Observable<TokenInfo> {
       return Observable.interval(interval, TimeUnit.SECONDS)
           .take(timeOut, TimeUnit.SECONDS)
           .concatMap {
               val call: Call<TokenInfo> =
                   service.getAccessToken(
                       clientId = CLIENT_ID,
                       deviceCode = deviceCode,
                       grantType = GRANT_TYPE
                   )

               Observable.create { emitter ->
                   kotlin.runCatching { call.execute() }
                       .onSuccess { response ->
                           Log.d(TAG, "getAccessToken request: Success!")

                           if (response.isSuccessful) {
                               response.body()?.let {
                                   Log.d(TAG, "error: ${it.error}")
                                   Log.d(TAG, "access_token: ${it.accessToken}")

                                   emitter.onNext(it)
                                   emitter.onComplete()
                               }
                           }
                       }
                       .onFailure {
                           Log.d(TAG, "getAccessToken request: Failure!")
                           emitter.onComplete()
                       }
               }
           }
   }
}