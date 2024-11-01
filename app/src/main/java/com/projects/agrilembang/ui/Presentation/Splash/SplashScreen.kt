package com.projects.agrilembang.ui.Presentation.Splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.R
import com.projects.agrilembang.datastore.UserPreferences
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val dataStore = UserPreferences(context = LocalContext.current)
    val isLoggedIn by dataStore.isLoggedIn.collectAsState(initial = false)


    LaunchedEffect(
        key1 = true,
    ) {
        delay(2000L)
        if (isLoggedIn) {
            navController.navigate(Screen.Beranda.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        } else {
            navController.navigate(Screen.Login.route) {
                popUpTo(Screen.Splash.route) {
                    inclusive = true
                }
            }
        }
    }
    Box(
        modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Image(painter = painterResource(id = R.drawable.backgroundsplash),
            contentDescription = "Splash Screen",
            modifier = modifier
                .fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Image(painter = painterResource(id = R.drawable.splashlogo),
            contentDescription = "Logo",
            modifier
                .height(164.dp)
                .width(166.dp)
        )
    }
}
