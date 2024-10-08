package com.projects.agrilembang.utils

import com.projects.agrilembang.navigation.Screen

fun String?.BottomBar(): Boolean {
    return this in setOf(
        Screen.Beranda.route,
        Screen.Suhu.route,
        Screen.Kelembapan.route,
        Screen.Riwayat.route
    )
}

fun String?.Fab(): Boolean {
    return this.toString() in Screen.Riwayat.route
}