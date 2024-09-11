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

@HiltViewModel
class ArchiveViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    var uiState by mutableStateOf(
        UIState(
            isLoading = false,
            error = "",
            listOfFiles = mutableStateListOf()
        )
    )

    fun fetchData(){

        repository.fetchData()
            .onStart {
                uiState = uiState.copy(isLoading = true, error = "")
            }
            .catch {
                uiState = uiState.copy(isLoading = false, error = it.message.toString())
            }
            .onEach { list ->
                val modifiedList = uiState.copy().listOfFiles
                modifiedList.addAll(list.map { ArchiveFile(
                    name = it.name.orEmpty(),
                    source = it.source.orEmpty(),
                    format = it.format.orEmpty()
                ) })

                uiState = uiState.copy(
                    isLoading = false,
                    error = "",
                    listOfFiles = modifiedList
                )
            }
            .launchIn(viewModelScope)

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