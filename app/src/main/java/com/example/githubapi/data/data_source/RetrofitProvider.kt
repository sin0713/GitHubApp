package com.example.githubapi.data.data_source

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RetrofitProvider {
    companion object {
        fun provideRetrofit(endPoint: String): Retrofit {
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()

            return Retrofit.Builder()
                .baseUrl(endPoint)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }
    }
}