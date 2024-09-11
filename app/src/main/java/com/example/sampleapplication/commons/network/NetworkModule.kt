package com.example.sampleapplication.commons.network

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface NetworkModule {

    @Binds
    fun bindNetworkMonitor(networkStateMonitorImpl: NetworkStateMonitorImpl): NetworkStateMonitor
}