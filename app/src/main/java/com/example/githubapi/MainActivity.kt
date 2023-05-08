package com.example.githubapi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import com.example.githubapi.ui.theme.GithubApiAppTheme
import com.example.githubapi.ui.view.HomeScreen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseAuthService.isLogin()

        setContent {
            GithubApiAppTheme {
                Scaffold(
                    backgroundColor = colorResource(id = R.color.app_background),
                    modifier = Modifier,
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    stringResource(id = R.string.app_name),
                                    color = Color.White
                                )
                            },
                            backgroundColor = colorResource(id = R.color.status_bar)
                        )
                    }
                ) { contentPadding ->
                    Spacer(modifier = Modifier.padding(contentPadding.calculateTopPadding()))
                    HomeScreen(activity = this)
                }
            }
        }
    }
}