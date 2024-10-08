package com.projects.agrilembang.ui.Components.fab

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Immutable
interface FabIcon {
    @Stable val expandIconRes : Int
    @Stable val collapseIconRes : Int
}

private class FabIconImpl(
    override val expandIconRes: Int,
    override val collapseIconRes: Int
): FabIcon

fun FabIcon(
    @DrawableRes expandIconRes: Int,
    @DrawableRes collapseIconRes: Int): FabIcon = FabIconImpl(expandIconRes, collapseIconRes)