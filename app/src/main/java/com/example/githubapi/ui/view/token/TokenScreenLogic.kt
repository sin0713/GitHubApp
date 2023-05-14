package com.example.githubapi.ui.view.token

import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.util.Log

class TokenScreenLogic {
    companion object {
        private const val TAG = "TokenScreenLogic"
    }

    fun launchBrowser(context: Context, url: String) {
        if (url.isEmpty()) return

        kotlin.runCatching { Uri.parse(url) }
            .onSuccess { uri ->
                context.startActivity(Intent(ACTION_VIEW, uri))
            }
            .onFailure { Log.d(TAG,"parse error") }
    }
}