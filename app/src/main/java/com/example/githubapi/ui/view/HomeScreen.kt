package com.example.githubapi.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.githubapi.R
import com.example.githubapi.data.pojo.RepositoryCard
import com.example.githubapi.ui.HomeUiState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val data = viewModel.cardData.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        HomeScreen(data.value)
        Button(
            onClick = {
                viewModel.searchRepository("codelab")
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 30.dp)
        ) {
            Text(text = "Search")
        }
    }
}

@Composable
fun HomeScreen(uiState: HomeUiState) {
    if (uiState.errorMessage.isNotEmpty()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = uiState.errorMessage,
                style = MaterialTheme.typography.headlineMedium
            )
        }
    } else {
        ResultList(cardData = uiState.data)
    }
    if (uiState.isLoading) {
        Box(
            Modifier
                .fillMaxSize()
                .background(
                    color = colorResource(id = R.color.loading_background)
                )
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun ResultList(cardData: List<RepositoryCard>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
            .padding(horizontal = 10.dp),
    ) {
        items(cardData) {data ->
            HomeCard(cardData = data)
        }
    }
}