package com.example.githubapi.ui.view.token

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun TokenScreen(
    viewModel: TokenViewModel = hiltViewModel(),
    activity: Activity
) {
    val data = viewModel.uiState.collectAsState()

    TokenScreen(
        uiState = data.value,
        launchBrowserFunc = {
            viewModel.logic.launchBrowser(
                activity,
                data.value.authenticationUrl
            )
        },
        getAccessTokenFunc = {
            viewModel.getAccessToken()
        }
    )
}

@Composable
fun TokenScreen(
    uiState: TokenUiState,
    launchBrowserFunc: () -> Unit = {},
    getAccessTokenFunc: () -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text("次の認証画面で下記の番号を入力してください")

        Text(
            uiState.userCode,
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 10.dp)
        )
        Button(
            onClick = {
                launchBrowserFunc()
                getAccessTokenFunc()
            },
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text("入力画面へ")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TokenScreenPreview() {
    val state = TokenUiState(
        "https://google.com",
        "000-000"
    )

    TokenScreen(state)
}