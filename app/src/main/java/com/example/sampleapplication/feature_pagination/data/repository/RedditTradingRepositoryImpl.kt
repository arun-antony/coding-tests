package com.example.sampleapplication.feature_pagination.data.repository

import com.example.sampleapplication.feature_pagination.data.RedditTradeListItem
import com.example.sampleapplication.feature_pagination.data.RedditTradesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RedditTradingRepositoryImpl @Inject constructor(val api: RedditTradesApi): RedditTradingRepository {

    override fun fetchData(): Flow<List<RedditTradeListItem>> = flow {
        val response = api.getRedditTradesList()

        if(response.isSuccessful){
            val list = response.body()

            list?.let{
                emit(list)
            }?:run{
                throw Exception("No data received")
            }
        }else{
            throw Exception("Api fetch failed ${response.message()}")
        }

    }
}

