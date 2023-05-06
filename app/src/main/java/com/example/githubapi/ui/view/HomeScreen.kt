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
import androidx.compose.material.OutlinedTextField
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
    val data = viewModel.homeUiSate.collectAsState()
    val uiState = data.value

    HomeScreen(
        state = uiState,
        shouldShowErrorUi = uiState.errorMessage.isNotEmpty(),
        errorMessage = uiState.errorMessage,
        onClickSearchButton = { viewModel.searchRepository()},
        updateSearchWord = { text -> viewModel.updateSearchWord(text) }
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    shouldShowErrorUi: Boolean,
    errorMessage: String,
    onClickSearchButton: () -> Unit,
    updateSearchWord: (String) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.searchWord,
            onValueChange = updateSearchWord,
            modifier = Modifier.padding(20.dp)
        )
        Button(
            onClick = onClickSearchButton,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = "Search")
        }
        if (shouldShowErrorUi) {
            Error(errorMessage)
        } else {
            ResultList(cardData = state.data)
        }
    }
    // ローディング中のみ表示
    Loading(isLoading = state.isLoading)
}

@Composable
fun Loading(isLoading: Boolean) {
    if (!isLoading) return

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

@Composable
fun Error(errorMessage: String = "") {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = errorMessage,
            style = MaterialTheme.typography.headlineMedium
        )
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