package com.projects.agrilembang.ui.Presentation.ForgotPassword

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.LoginViewModel
import com.projects.agrilembang.ui.Components.Button.CustomButton
import com.projects.agrilembang.ui.Components.TextField.CustomTextField
import com.projects.agrilembang.ui.theme.poppinsmedium
import com.projects.agrilembang.ui.theme.poppinsregular
import com.projects.agrilembang.ui.theme.poppinssemibold
import kotlinx.coroutines.launch

@SuppressLint("InvalidColorHexValue")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val sheetState = rememberModalBottomSheetState()
    var isBottomSheetShow by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val state by viewModel.state.collectAsState(initial = null)

    if (isBottomSheetShow) {
        ModalBottomSheet(
            onDismissRequest = {
                isBottomSheetShow = false
            },
            sheetState = sheetState
        ) {
            Column (
                modifier = Modifier
                    .padding(10.dp)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .windowInsetsPadding(WindowInsets.ime),
                verticalArrangement = Arrangement.spacedBy(7.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Image(
                    painter = painterResource(R.drawable.iconbottomsheet),
                    contentDescription = "Icon Bottom Sheet",
                    modifier = Modifier
                        .size(325.dp, 250.dp)
                )
                Text(
                    text = "Silahkan buka email anda untuk melakukan perbaruan kata sandi",
                    fontSize = 15.sp,
                    fontFamily = poppinsmedium,
                    textAlign = TextAlign.Center
                )
                CustomButton(
                    onClickedAction = {
                        isBottomSheetShow = false
                    },
                    modifier = Modifier
                        .size(325.dp, 40.dp ),
                    title = "Selesai"
                )
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        IconButton(onClick = {
            navController.navigate(Screen.Login.route){
                popUpTo(Screen.ForgotPassword.route){
                    inclusive = true
                }
            }
        },
            modifier = Modifier
                .size(13.dp, 20.dp)
            ) {
            Image(painter = painterResource(id = R.drawable.backbutton),
                contentDescription = "Icon Back",
                modifier = Modifier
                    .size(13.dp, 20.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp)
        )
        Text(
            text = "Lupa Kata Sandi",
            fontFamily = poppinssemibold,
            fontSize = 24.sp,
        )
        Text(
            text = "Silakan masukkan email Anda untuk mengatur ulang kata sandi",
            fontFamily = poppinsregular,
            fontSize = 14.sp,
            color = Color(0xFFAEAEAE)
        )
        Text(
            text = "Email Anda",
            fontSize = 18.sp,
            fontFamily = poppinsmedium,
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder = "Masukkan Email Anda",
                isPassword = false,
                modifier = Modifier
                    .size(346.dp, 55.dp)
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            CustomButton(
                onClickedAction = {
                    coroutineScope.launch {
                        viewModel.resetPassword(email) {
                            if (it) {
                                isBottomSheetShow = true
                            } else {
                                Toast.makeText(context, "Email not found", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                },
                title = "Perbarui Kata Sandi",
                modifier = Modifier
                    .size(346.dp, 55.dp))
        }
    }
}
