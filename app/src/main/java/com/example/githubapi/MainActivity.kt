package com.example.githubapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.githubapi.data.data_source.SharedPrefClient
import com.example.githubapi.ui.theme.GithubApiAppTheme
import com.example.githubapi.ui.view.home.HomeScreen
import com.example.githubapi.ui.view.token.TokenScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object {
        const val DESTINATION_HOME: String = "home"
        const val DESTINATION_TOKEN: String = "token"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        SharedPrefClient.init(this)

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
                    val controller = rememberNavController()

                    NavHost(
                        navController = controller,
                        startDestination = DESTINATION_HOME,
                        modifier = Modifier.padding(contentPadding)
                    ) {
                        composable(DESTINATION_HOME) {
                            HomeScreen(navController = controller)
                        }
                        composable(DESTINATION_TOKEN) {
                            TokenScreen(activity = this@MainActivity)
                        }
                    }
                }
            }
        }
    }
}