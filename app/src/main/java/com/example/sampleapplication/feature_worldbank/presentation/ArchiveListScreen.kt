package com.example.sampleapplication.feature_worldbank.presentation

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sampleapplication.commons.network.NetworkState

@Composable
fun ArchiveListScreen(
    modifier: Modifier = Modifier,
    viewModel: ArchiveViewModel = hiltViewModel(),
    networkState: NetworkState,
    showSnackBar: (String)-> Unit
){
    LaunchedEffect(key1 = networkState) {
        Log.i("NETWORK", networkState.toString())
       if(networkState == NetworkState.Connected){
           viewModel.fetchData()
       }

        if(networkState == NetworkState.Disconnected){
            showSnackBar("Connectivity lost")
        }
    }
//    LaunchedEffect(key1 = Unit) {
//        viewModel.fetchData()
//    }

    with(viewModel.uiState) {
        if (isLoading) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        if (error.isNotEmpty()) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = error)
            }
        }

        if(listOfFiles.isNotEmpty()){

            LazyColumn (modifier = modifier
                .fillMaxSize()
                .padding(16.dp)) {

                itemsIndexed(
                    items = listOfFiles,
                    key = { index, _ -> index}
                ){ _, item ->
                    Card(modifier = Modifier.padding(8.dp), onClick = { }) {

                        Column {
                            Text(text = item.name)
                            Text(text = item.source)
                            Text(text = item.format)
                        }
                    }
                }
            }
        }
    }
}