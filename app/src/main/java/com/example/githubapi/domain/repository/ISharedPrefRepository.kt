package com.example.githubapi.domain.repository

interface ISharedPrefRepository {
    fun getStringData(key: String): String
    fun putString(key: String, value: String)
}