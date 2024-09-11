package com.example.sampleapplication.feature_pagination.data.di

import com.example.sampleapplication.feature_pagination.data.repository.RedditTradingRepository
import com.example.sampleapplication.feature_pagination.data.repository.RedditTradingRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindRepository(repo: RedditTradingRepositoryImpl): RedditTradingRepository
}