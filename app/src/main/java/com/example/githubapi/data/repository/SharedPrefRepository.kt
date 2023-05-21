package com.example.githubapi.data.repository

import android.content.SharedPreferences
import com.example.githubapi.domain.repository.ISharedPrefRepository
import javax.inject.Inject

class SharedPrefRepository @Inject constructor() : ISharedPrefRepository {
    @Inject lateinit var sharedPref: SharedPreferences

    override fun getStringData(key: String): String {
        if (key.isBlank()) return ""

        return sharedPref.getString(key, "") ?: ""
    }

    override fun putString(key: String, value: String) {
        TODO("Not yet implemented")
    }
}