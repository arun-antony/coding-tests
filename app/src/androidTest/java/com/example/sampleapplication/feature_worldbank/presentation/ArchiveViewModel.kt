package com.example.sampleapplication.feature_worldbank.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapplication.feature_worldbank.data.ArchiveRepository
import com.example.sampleapplication.feature_worldbank.data.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class ArchiveViewModel: ViewModel() {

    var uiState by mutableStateOf(
        UIState(
            isLoading = false,
            error = "",
            listOfFiles = mutableStateListOf()
        )
    )

    fun fetchData(){

        val modifiedList = uiState.copy().listOfFiles
        modifiedList.addAll(
            listOf(ArchiveFile(
            name = "ABC",
            source = "src",
            format = "format"
        ) ))

        uiState = uiState.copy(
            isLoading = false,
            error = "",
            listOfFiles = modifiedList
        )

    }
}

data class UIState(
    var isLoading: Boolean,
    var error: String,
    var listOfFiles: MutableList<ArchiveFile>
)

data class ArchiveFile(
    var name: String,
    var source: String,
    var format: String
)