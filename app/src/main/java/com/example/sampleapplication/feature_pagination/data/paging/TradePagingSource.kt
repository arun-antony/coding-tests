package com.example.sampleapplication.feature_pagination.data.paging

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.sampleapplication.feature_pagination.data.RedditTradesApi
import com.example.sampleapplication.feature_pagination.data.repository.RedditTradingRepositoryImpl
import com.example.sampleapplication.feature_pagination.presentation.TradeListItem
import kotlinx.coroutines.flow.map

class TradePagingSource(private val api: RedditTradesApi): PagingSource<Int, TradeListItem>() {
    override fun getRefreshKey(state: PagingState<Int, TradeListItem>): Int? {
       return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TradeListItem> {
        val currentPage = params.key ?: 1

        lateinit var result: LoadResult<Int, TradeListItem>
        try {
            val response = api.getRedditTradesList()

            if(response.isSuccessful){
                val list: List<TradeListItem> = (response.body()?: emptyList()).map { TradeListItem(sentiment = it.sentiment, ticker = it.ticker) }
                //result = LoadResult.Page(data = list, prevKey = currentPage, nextKey = currentPage+1)
                result = LoadResult.Page(data = list, prevKey = currentPage, nextKey = currentPage +1 )
            }
        }catch (e: Exception){
            LoadState.Error(e)
        }

        return result

    }
}