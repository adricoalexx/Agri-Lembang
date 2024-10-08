package com.projects.agrilembang.ui.Components.BottomBar

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.R
import com.projects.agrilembang.utils.BottomBar
import com.projects.agrilembang.ui.theme.intermedium

@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController
) {
    BackHandler(enabled = true){

    }
    NavigationBar(
        modifier = modifier
            .height(65.dp)
            .fillMaxWidth()
            .graphicsLayer {
                clip = true
                shadowElevation = 10.dp.toPx()
            },
        containerColor = Color.White,
        contentColor = Color.White,
        windowInsets = WindowInsets.ime
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        


        val navigationItems = listOf(
            BottomBar(
                title = "Beranda",
                icon = painterResource(id = R.drawable.home),
                selectedIcon = painterResource(id = R.drawable.homeclicked),
                screen = Screen.Beranda
            ),
            BottomBar(
                title = "Suhu",
                icon = painterResource(id = R.drawable.suhu),
                selectedIcon = painterResource(id = R.drawable.suhuclicked),
                screen = Screen.Suhu
            ),
            BottomBar(
                title = "Kelembapan",
                icon = painterResource(id = R.drawable.kelembapan),
                selectedIcon = painterResource(id = R.drawable.kelembapanclicked),
                screen = Screen.Kelembapan
            ),
            BottomBar(
                title = "Riwayat",
                icon = painterResource(id = R.drawable.riwayat),
                selectedIcon = painterResource(id = R.drawable.riwayatclicked),
                screen = Screen.Riwayat
            )
        )

        navigationItems.forEachIndexed { index, item ->
            val selected = currentRoute == item.screen.route

            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route){
                        popUpTo(navController.graph.findStartDestination().id) {
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Transparent,
                    unselectedIconColor = Color.Transparent,
                    selectedTextColor = Color.Transparent,
                    unselectedTextColor = Color.Transparent,
                    indicatorColor = Color.Transparent,


                ),
                icon = {
                    Image(painter = if (selected) item.selectedIcon else item.icon,
                        contentDescription = item.title,
                        modifier
                            .size(25.dp)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 9.sp,
                        fontFamily = intermedium,
                        color = if (selected) Color(0xFF155B36) else Color(0xFF898989)
                    )
                },
            )
        }
    }
}