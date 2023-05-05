package com.example.githubapi.ui.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rxjava3.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubapi.data.pojo.RepositoryCard
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    val data = viewModel.cardData.subscribeAsState(initial = listOf())

    HomeScreen(data.value)
}

@Composable
fun HomeScreen(cardData: List<RepositoryCard>) {
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