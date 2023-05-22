package com.example.githubapi.data.util

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call

object RxJavaAdapter {
    private const val TAG = "RxJavaAdapter"

    fun <T: Any> request(call: Call<T>): Observable<T> {
        return Observable.create { emitter ->
            kotlin.runCatching { call.execute() }
                .onSuccess { response ->
                    Log.d(TAG, "Success!")

                    if (response.isSuccessful) {
                        response.body()?.let { body ->

                            emitter.onNext(body)
                            emitter.onComplete()
                        }
                    }
                }
                .onFailure {
                    Log.d(TAG, "Failure!")
                    emitter.onError(it)
                }
        }
    }
}