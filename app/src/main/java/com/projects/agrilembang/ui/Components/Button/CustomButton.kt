package com.projects.agrilembang.ui.Components.Button

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.agrilembang.ui.theme.intersemibold

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onClickedAction : () -> Unit,
    title : String,
) {
    Button(
        onClick = {
        onClickedAction()
    },
        colors = ButtonDefaults.buttonColors(Color(0xFF155B36)),
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontFamily = intersemibold,
            color = Color(0xFFFFFFFF)
        )
    }

}

@Preview
@Composable
private fun Prev() {
    CustomButton(onClickedAction = { }, title = "Adrico")
}