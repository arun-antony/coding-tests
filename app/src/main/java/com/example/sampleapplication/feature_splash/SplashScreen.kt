package com.example.sampleapplication.feature_splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    duration: Long = 3000L,
    onElapsed: ()-> Unit){

    val currentOnElapsed by rememberUpdatedState(newValue = onElapsed)

    LaunchedEffect(key1 = Unit) {
        delay(duration)
        currentOnElapsed()
    }

    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Icon(imageVector = Icons.Filled.Face, contentDescription = "Splash")
    }

}