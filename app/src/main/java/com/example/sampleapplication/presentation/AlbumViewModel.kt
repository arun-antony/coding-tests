package com.example.sampleapplication.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapplication.data.AlbumRepository
import com.example.sampleapplication.data.AlbumRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val repository: AlbumRepository): ViewModel() {

    var uiState: UIState by mutableStateOf(
        UIState()
    )

    fun fetchData(){
        uiState = uiState.copy(isLoading = true, loadError = "")

        viewModelScope.launch {
            repository.getAlbumData().collect{ result ->

                when{

                    result.isSuccess-> {
                        val list = result.getOrNull().orEmpty()

                        uiState = uiState
                            .copy(
                                isLoading = false,
                                loadError = if(list.isEmpty()) "No data available" else "",
                                albumList = list
                                    .map { AlbumModel(it.title, it.id) }
                                    .toMutableStateList())
                    }

                    else -> {
                        uiState = uiState
                            .copy(
                                isLoading = false,
                                loadError = result.exceptionOrNull()?.message?:"Error fetching data",
                                albumList = mutableStateListOf()
                            )
                    }
                }

            }
        }
    }
}

data class UIState(
    var albumList: MutableList<AlbumModel> = mutableStateListOf(),
    var isLoading: Boolean = false,
    var loadError: String = ""
)

data class AlbumModel(
    val title: String,
    val id: Int
)