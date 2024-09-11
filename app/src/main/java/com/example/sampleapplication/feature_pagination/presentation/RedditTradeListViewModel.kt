package com.example.sampleapplication.feature_pagination.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.sampleapplication.feature_pagination.data.RedditTradeListItem
import com.example.sampleapplication.feature_pagination.data.paging.TradePagingSource
import com.example.sampleapplication.feature_pagination.data.repository.RedditTradingRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class RedditTradeListViewModel @Inject constructor(
    private val repository: RedditTradingRepositoryImpl) : ViewModel(){

        var uiState by mutableStateOf(
            UIState()
        )

        val pagingConfig = PagingConfig(
            pageSize = 20,
            prefetchDistance = 5,
            initialLoadSize = 20
        )

        val pagingDataFlow = Pager(pagingConfig){
            TradePagingSource(repository.api)
        }.flow.cachedIn(viewModelScope)

        fun fetchData(){
            repository.fetchData()
                .onStart {
                    uiState = uiState.copy(
                        loadInProgress = true,
                        errorMessage = ""
                    )
                }
                .catch {
                    uiState = uiState.copy(
                        loadInProgress = false,
                        errorMessage = it.message.toString()
                    )
                }
                .map { originalList ->
                    originalList.map { TradeListItem(ticker = it.ticker, sentiment = it.sentiment) }
                }
                .onEach {
                    val list = uiState.copy().list
                    list.addAll(it)
                    uiState = uiState.copy(
                        loadInProgress = false,
                        errorMessage = ""
                    )
                }.launchIn(viewModelScope)
        }
}

data class UIState(
    val loadInProgress: Boolean = false,
    val errorMessage: String = "",
    val list: MutableList<TradeListItem> = mutableStateListOf()
)

data class TradeListItem(
    val sentiment: String = "",
    val ticker: String = ""
)