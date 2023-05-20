package com.example.githubapi.ui.view.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubapi.MainActivity
import com.example.githubapi.R
import com.example.githubapi.domain.model.RepositoryModel
import com.example.githubapi.ui.HomeUiState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController,
) {
    val data = viewModel.homeUiSate.collectAsState()
    val uiState = data.value

    HomeScreen(
        state = uiState,
        shouldShowErrorUi = uiState.errorMessage.isNotEmpty(),
        errorMessage = uiState.errorMessage,
        navigateFunc = { navController.navigate(MainActivity.DESTINATION_TOKEN)},
        onClickSearchButton = { viewModel.searchRepository()},
        updateSearchWord = { text -> viewModel.updateSearchWord(text) },
        updateDialogState = { viewModel.updateDialogState(it) },
    )
}

@Composable
fun HomeScreen(
    state: HomeUiState,
    shouldShowErrorUi: Boolean,
    errorMessage: String,
    navigateFunc: () -> Unit,
    onClickSearchButton: () -> Unit,
    updateSearchWord: (String) -> Unit,
    updateDialogState: (Boolean) -> Unit,
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
        Button(
            onClick = { updateDialogState(true) },
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Text(text = "show dialog")
        }
        if (shouldShowErrorUi) {
            Error(errorMessage)
        } else {
            ResultList(cardData = state.data)
        }
    }

    // showDialog
    GetTokenDialog(
        showDialog = state.showDialog,
        dismissDialog = { updateDialogState(false) },
        requestFunction = navigateFunc
    )

    // ローディング中のみ表示
    Loading(isLoading = state.isLoading)
}

@Composable
fun GetTokenDialog(
    showDialog: Boolean,
    dismissDialog: () -> Unit,
    requestFunction:  () -> Unit,
) {
    if (!showDialog) return

    AlertDialog(
        onDismissRequest = dismissDialog,
        confirmButton = {
            TextButton(
                onClick = requestFunction,
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            TextButton(
                onClick = dismissDialog,
            ) {
                Text(text = "Cancel")
            }
        },
        title = {
            Text("Please get token")
        },
        text = {
            Text("This app need to get token.")
        }
    )
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
fun ResultList(cardData: List<RepositoryModel>) {
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