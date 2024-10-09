package com.example.sampleapplication.feature_people.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PeopleListScreen(modifier: Modifier = Modifier, viewModel: PeopleViewModel = hiltViewModel()){

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchData()
    }

    with(viewModel.uiState){

        if(isLoading){
            Box(modifier = modifier){
                CircularProgressIndicator()
            }
        }

        if(errorMessage.isNotEmpty()){
            Box(modifier = modifier){
                Text(text = errorMessage)
            }
        }

        LazyColumn {
            itemsIndexed(
                items = list,
                key = { index, _ -> index}){ _, item ->

                Card {
                    Column {
                        Text(text = item.name)
                        Text(text = item.dob)
                    }
                }
            }
        }
    }
}