package com.example.sampleapplication.feature_pagination.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun RedditTradeListScreen(
    modifier: Modifier = Modifier,
    viewModel: RedditTradeListViewModel = hiltViewModel()
){
    val listData = viewModel.pagingDataFlow.collectAsLazyPagingItems()

    TradeListScreen(modifier = modifier, uiState = viewModel.uiState, listData)
}

@Composable
fun TradeListScreen(
    modifier: Modifier,
    uiState: UIState,
    listData: LazyPagingItems<TradeListItem>
){

    if(uiState.loadInProgress){
        Box(modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            CircularProgressIndicator()
        }
    }

    if(uiState.errorMessage.isNotEmpty()){
        Box(modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            Text(text = uiState.errorMessage)
        }
    }

    LazyColumn {
        listData.apply {
            when(loadState.append){
                is LoadState.Loading ->{

                }

                is LoadState.Error -> {


                }
                is LoadState.NotLoading -> {

                }
            }
        }
        items(count = listData.itemCount){ index ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)) {
                Column(modifier = modifier.padding(8.dp)) {
                    Text(text = listData[index]?.ticker?:"", fontSize = 24.sp)
                    //Text(text = item.sentiment)
                }

            }
        }

    }
}