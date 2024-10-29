package com.projects.agrilembang

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.projects.agrilembang.firebase.SensorViewModel
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.utils.BottomBar
import com.projects.agrilembang.ui.Components.BottomBar.BottomBar
import com.projects.agrilembang.ui.Components.fab.FabIcon
import com.projects.agrilembang.ui.Components.fab.FabOption
import com.projects.agrilembang.ui.Components.fab.MultiFabItem
import com.projects.agrilembang.ui.Components.fab.MultiFloatingActionButton
import com.projects.agrilembang.ui.Components.fab.fabOption
import com.projects.agrilembang.ui.Presentation.ForgotPassword.ForgotPasswordScreen
import com.projects.agrilembang.ui.Presentation.Login.LoginScreen
import com.projects.agrilembang.ui.Presentation.Menu.Beranda.BerandaScreen
import com.projects.agrilembang.ui.Presentation.Menu.Kelembapan.KelembapanScreen
import com.projects.agrilembang.ui.Presentation.Menu.Riwayat.RiwayatScreen
import com.projects.agrilembang.ui.Presentation.Menu.Suhu.SuhuScreen
import com.projects.agrilembang.ui.Presentation.Profile.AccountDetail
import com.projects.agrilembang.ui.Presentation.Profile.ProfileScreen
import com.projects.agrilembang.ui.Presentation.Register.RegisterScreen
import com.projects.agrilembang.ui.Presentation.Splash.SplashScreen
import com.projects.agrilembang.utils.Fab

@Composable
fun AgriLembangApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val context = LocalContext.current
    val fabIcon = FabIcon(
        expandIconRes = R.drawable.closeicon,
        collapseIconRes = R.drawable.printicon
    )

    Scaffold (
        bottomBar = {
            AnimatedVisibility(visible = currentRoute.BottomBar()) {
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            AnimatedVisibility(visible = currentRoute.Fab()) {
                MultiFloatingActionButton(
                    items = listOf(
                        MultiFabItem(
                            id = 1,
                            iconRes = R.drawable.csvicon,
                            label = "CSV"
                        ),
                        MultiFabItem(
                            id = 2,
                            iconRes = R.drawable.pdficon,
                            label = "PDF"
                        ),
                    ),
                    fabIcon = fabIcon,
                    onFabItemClicked = {
                        Toast.makeText(context, it.label, Toast.LENGTH_SHORT).show()
                    },
                    fabOption = fabOption(
                        iconTint = Color.White,
                        showLabel = true
                    )
                )
            }
        }
    ){
        NavHost(navController = navController,
            startDestination = Screen.Splash.route,
            modifier.padding(it)) {
            composable(Screen.Splash.route){
                SplashScreen(navController = navController)
            }
            composable(Screen.Login.route){
                LoginScreen(navController = navController)
            }
            composable(Screen.Register.route){
                RegisterScreen(navController = navController)
            }
            composable(Screen.ForgotPassword.route){
                ForgotPasswordScreen(navController = navController)
            }
            composable(Screen.Beranda.route){
                BerandaScreen(navController = navController)
            }
            composable(Screen.Kelembapan.route){
                KelembapanScreen()
            }
            composable(Screen.Suhu.route){
                SuhuScreen()
            }
            composable(Screen.Riwayat.route){
                RiwayatScreen()
            }
            composable(Screen.Profile.route){
                ProfileScreen(navController = navController)
            }
            composable(Screen.ProfileDetail.route){
                AccountDetail(navController = navController)
            }
        }
    }
}