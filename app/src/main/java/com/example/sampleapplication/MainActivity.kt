package com.example.sampleapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.sampleapplication.commons.network.NetworkState
import com.example.sampleapplication.commons.network.NetworkStateMonitorImpl
import com.example.sampleapplication.feature_worldbank.presentation.ArchiveViewModel
import com.example.sampleapplication.nav.AppNavHost
import com.example.sampleapplication.nav.Routes
import com.example.sampleapplication.ui.theme.SampleApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var  networkStateMonitorImpl: NetworkStateMonitorImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val snackBarHostState = remember {
                SnackbarHostState()
            }

            val navHostController = rememberNavController()
            val startDestination = remember {
                Routes.Splash.name
            }


            val scope = rememberCoroutineScope()

            val (networkState, setState) = remember{
                 mutableStateOf( if(networkStateMonitorImpl.isOnline()) NetworkState.Connected else NetworkState.Disconnected)
            }

            LaunchedEffect(key1 = Unit) {
                networkStateMonitorImpl.observeState().onEach {
                    setState(it)
                }.collect()
            }

            val viewModel: ArchiveViewModel = hiltViewModel()

            SampleApplicationTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState)
                    }) { innerPadding ->

                    AppNavHost(
                        modifier = Modifier.padding(innerPadding),
                        navHostController = navHostController,
                        startDestination = startDestination,
                        networkState = networkState,
                        showSnackBar = {
                            showSnackBar(scope, snackBarHostState, it)
                        }
                    )

                }
            }
        }
    }
}

fun showSnackBar(scope: CoroutineScope,
                 snackBarHostState: SnackbarHostState,
                 message: String){
    scope.launch {
        snackBarHostState.showSnackbar(
            message = message
        )
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SampleApplicationTheme {
        Greeting("Android")
    }
}