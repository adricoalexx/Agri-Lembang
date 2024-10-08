package com.projects.agrilembang.ui.Components.fab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

sealed class MultiFabState {
    object Collapse: MultiFabState()
    object Expand: MultiFabState()

    fun isExpanded() = this == Expand

    fun toggleValue() = if (isExpanded()){
        Collapse
    } else {
        Expand
    }
}

@Composable
fun rememberMultiFabState() = remember { mutableStateOf<MultiFabState>(MultiFabState.Collapse) }