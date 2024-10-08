package com.projects.agrilembang.ui.Components.TextField

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.agrilembang.ui.theme.poppinssemibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OTPTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    onBackspacePressed: () -> Unit,
    isFilled: Boolean
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Backspace && value.isEmpty()) {
                    onBackspacePressed()
                    true
                } else {
                    false
                }
            },
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontFamily = poppinssemibold,
            textAlign = TextAlign.Center
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = if (isFilled) Color(0xFF155B36) else Color(0xFFC4C4C4)
            , unfocusedTextColor = if (isFilled) Color(0xFF155B36) else Color(0xFFC4C4C4)
            , focusedBorderColor = if (isFilled) Color(0xFF155B36) else Color(0xFFC4C4C4)
            , unfocusedBorderColor = if (isFilled) Color(0xFF155B36) else Color(0xFFC4C4C4)
        ),
        shape = RoundedCornerShape(8.dp)
    )
}