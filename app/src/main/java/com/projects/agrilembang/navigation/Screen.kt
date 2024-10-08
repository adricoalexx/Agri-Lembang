package com.projects.agrilembang.navigation

sealed class Screen(val route: String){
    data object Splash : Screen("Splash")
    data object Login : Screen("Login")
    data object Register : Screen("Register")
    data object ForgotPassword : Screen("forgotpassword")
    data object Beranda : Screen("beranda")
    data object Kelembapan : Screen("kelembapan")
    data object Riwayat : Screen("riwayat")
    data object Suhu : Screen("suhu")
    data object Profile : Screen("profile")
    data object ProfileDetail : Screen("profiledetail")
}
