package com.projects.agrilembang.utils

import androidx.compose.ui.graphics.painter.Painter
import com.projects.agrilembang.navigation.Screen

data class BottomBar(
    val title: String,
    val icon: Painter,
    val selectedIcon: Painter,
    val screen: Screen
)
