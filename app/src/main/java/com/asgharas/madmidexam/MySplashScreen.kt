package com.asgharas.madmidexam

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import kotlinx.coroutines.delay

@Composable
fun MySplashScreen(navigateToMainScreen: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(3000) //Delay for 3 Seconds
        //Code to move to next screen
        navigateToMainScreen()
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.ic_launcher), contentDescription = "Logo")
    }
}