package com.example.sampleapplication.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun AlbumListingScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumViewModel = hiltViewModel()
){

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
    }

    LazyColumn(modifier = modifier.fillMaxSize()) {

        itemsIndexed(
            items = viewModel.uiState.albumList,
            key = { index, _ ->
                index
            },
            itemContent = { _, albumModel ->

                Card(onClick = { /*TODO*/ }) {
                    Text(text = albumModel.title)
                }
            }
        )
    }
}