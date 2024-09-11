package com.example.sampleapplication.commons.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.util.Log
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkStateMonitorImpl @Inject constructor(@ActivityContext val context: Context): NetworkStateMonitor {

    private val connectivityService = context.getSystemService(ConnectivityManager::class.java) as ConnectivityManager


    private val networkRequest: NetworkRequest = NetworkRequest.Builder()
        .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
        .build()

    override fun observeState(): Flow<NetworkState> = callbackFlow{
        val networkCallBack = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.i("NETWORK", "onAvailable")
                trySend(NetworkState.Connected)
            }

            override fun onUnavailable() {
                Log.i("NETWORK", "unavailable")
                trySend(NetworkState.Disconnected)
            }

            override fun onLost(network: Network) {
                Log.i("NETWORK", "lost")
                trySend(NetworkState.Disconnected)
            }
        }

        connectivityService.registerNetworkCallback(networkRequest, networkCallBack)

        awaitClose {
            connectivityService.unregisterNetworkCallback(networkCallBack)
        }
    }.distinctUntilChanged().flowOn(Dispatchers.IO)

    override fun isOnline(): Boolean {
        return connectivityService.activeNetworkInfo?.isConnectedOrConnecting?:false
    }
}