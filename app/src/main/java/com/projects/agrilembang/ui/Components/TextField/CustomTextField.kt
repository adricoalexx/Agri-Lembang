package com.projects.agrilembang.ui.Components.TextField

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.agrilembang.R
import com.projects.agrilembang.ui.theme.intersemibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder : String,
    isPassword : Boolean,
    trailingIcon: @Composable (() -> Unit)? = null
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        singleLine = true,
        placeholder = {
            Text(
                text = placeholder
                ,color = Color(0xFFC4C4C4)
                , fontFamily = intersemibold
                , fontSize = 12.sp
            )
        },
        textStyle = TextStyle(
            color = Color.Black,
            fontFamily = intersemibold,
            fontSize = 12.sp
        ),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedTextColor = Color(0xFF155B36)
            , unfocusedTextColor = Color(0xFFC4C4C4)
            , focusedBorderColor = Color(0xFF155B36)
            , unfocusedBorderColor = Color(0xFFC4C4C4)
        ),
        shape = RoundedCornerShape(8.dp),
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        trailingIcon = trailingIcon
    )
}
