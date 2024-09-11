package com.example.sampleapplication.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sampleapplication.commons.network.NetworkState
import com.example.sampleapplication.feature_splash.SplashScreen
import com.example.sampleapplication.feature_worldbank.presentation.ArchiveListScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    startDestination: String = Routes.Splash.name,
    networkState: NetworkState,
    showSnackBar: (String)-> Unit
){

    NavHost(navController = navHostController, startDestination = startDestination) {

        composable(Routes.Splash.name){
            SplashScreen {

                navHostController.navigate(Routes.Bank.name) {
                    navHostController.popBackStack()
                }
            }
        }

        composable(Routes.Bank.name){
            ArchiveListScreen(networkState = networkState,
                showSnackBar = showSnackBar)
        }
    }
}

sealed class Routes(val name: String){

    data object Splash: Routes("splash")
    data object Bank: Routes("bank")
}