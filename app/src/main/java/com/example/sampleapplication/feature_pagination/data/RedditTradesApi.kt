package com.example.sampleapplication.feature_pagination.data

import com.google.gson.annotations.SerializedName
import dagger.Module
import dagger.hilt.InstallIn
import retrofit2.Response
import retrofit2.http.GET

interface RedditTradesApi {

    @GET("api/v1/apps/reddit")
    suspend fun getRedditTradesList(): Response<List<RedditTradeListItem>>
}

data class RedditTradeListItem(

    @SerializedName("no_of_comments")
    var noOfComments: Int,

    var sentiment: String = "",

    @SerializedName("sentiment_score")
    var sentimentScore: Float,

    var ticker: String = ""
)

