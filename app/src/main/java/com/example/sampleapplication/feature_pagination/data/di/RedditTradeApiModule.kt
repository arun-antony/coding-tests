package com.example.sampleapplication.feature_pagination.data.di

import com.example.sampleapplication.feature_pagination.data.RedditTradesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

@Module
@InstallIn(ViewModelComponent::class)
object RedditTradeApiModule {

    @Provides
    fun provide(retrofit: Retrofit): RedditTradesApi{
        return retrofit.create(RedditTradesApi::class.java)
    }
}