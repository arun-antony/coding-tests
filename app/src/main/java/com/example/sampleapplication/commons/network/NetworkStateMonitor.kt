package com.example.sampleapplication.commons.network

import kotlinx.coroutines.flow.Flow


sealed class NetworkState{
    data object Connected: NetworkState()
    data object Disconnected: NetworkState()
    data object Flaky: NetworkState()
}

interface NetworkStateMonitor {
    fun observeState(): Flow<NetworkState>
    fun isOnline(): Boolean
}

