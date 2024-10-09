package com.example.sampleapplication.feature_people.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sampleapplication.feature_people.data.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: PeopleRepository
): ViewModel() {

    var uiState: UIState by mutableStateOf(
        UIState()
    )

    fun fetchData(){
        repository.getPeople()
            .onStart {
                uiState = uiState.copy(
                    isLoading = true,
                    errorMessage = ""
                )
            }
            .catch {
                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = it.message.toString()
                )
            }
            .map { result ->

                result.results.filter {
                    it.birthYear == "19BBY"
                }.map{
                    PeopleItemModel(
                        name = it.name,
                        dob = it.birthYear
                    )
                }
            }.onEach {

                val list = uiState.copy().list
                list.addAll(it)

                uiState = uiState.copy(
                    isLoading = false,
                    errorMessage = "",
                    list = list
                )
            }.launchIn(viewModelScope)
    }


}

data class UIState(
    val isLoading: Boolean = false,
    val errorMessage: String = "",
    val list: MutableList<PeopleItemModel> = mutableStateListOf()
)

data class PeopleItemModel(
    val name: String,
    val dob: String
)