package com.example.githubapi.data.data_source

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

object SharedPrefClient {
    private const val FILE_NAME: String = "secret_prefs"
    const val TOKEN_KEY: String = "secret_token"
    private lateinit var masterKey: MasterKey
    private lateinit var _sharedPref: SharedPreferences

    fun init(context: Context) {
        masterKey = MasterKey.Builder(context)
         .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
         .build()

        _sharedPref = EncryptedSharedPreferences.create(
            context,
            FILE_NAME,
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    fun putStr(key: String, value: String) {
        _sharedPref.edit().putString(key, value).apply()
    }

    fun getStr(key: String): String {
        return _sharedPref.getString(key, "") ?: ""
    }
}