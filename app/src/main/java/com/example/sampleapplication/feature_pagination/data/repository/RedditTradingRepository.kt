package com.example.sampleapplication.feature_pagination.data.repository

import com.example.sampleapplication.feature_pagination.data.RedditTradeListItem
import kotlinx.coroutines.flow.Flow

interface RedditTradingRepository {

    fun fetchData(): Flow<List<RedditTradeListItem>>

}