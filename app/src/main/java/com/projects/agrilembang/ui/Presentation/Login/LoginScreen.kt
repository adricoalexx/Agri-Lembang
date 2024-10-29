package com.projects.agrilembang.ui.Presentation.Login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.R
import com.projects.agrilembang.datastore.SharedPreferencesManager
import com.projects.agrilembang.datastore.UserPreferences
import com.projects.agrilembang.firebase.LoginViewModel
import com.projects.agrilembang.ui.Components.Button.CustomButton
import com.projects.agrilembang.ui.Components.TextField.CustomTextField
import com.projects.agrilembang.ui.theme.intermedium
import com.projects.agrilembang.ui.theme.intersemibold
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isShowed by remember { mutableStateOf(false ) }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState(initial = null)
    val dataStore = remember { UserPreferences(context) }
    val sharedPreferencesManager = remember { SharedPreferencesManager(context) }

    Column(
        modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Masuk",
            fontSize = 24.sp,
            fontFamily = intersemibold
        )
        Image(painter = painterResource(
            id = R.drawable.logoagrilembang),
            contentDescription = "Logo Agri Lembang",
            modifier
                .size(160.dp)
        )
        Column(
            modifier
                .fillMaxSize()
                .padding(start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Text(
                text = "Email",
                fontSize = 16.sp,
                fontFamily = intermedium
            )
            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                modifier = Modifier
                    .size(347.dp, 55.dp),
                placeholder = "Masukkan Email Anda",
                isPassword = false
            )
            Text(
                text = "Kata Sandi",
                fontSize = 16.sp,
                fontFamily = intermedium
            )
            CustomTextField(
                value = password,
                onValueChange = {
                    password = it
                },
                placeholder = "Masukan Kata Sandi",
                isPassword = !isShowed,
                modifier = modifier
                    .size(347.dp, 55.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isShowed = !isShowed
                        }
                    ) {
                        Image(
                            painter = painterResource(
                                if (isShowed){
                                    R.drawable.iconshowpassword
                                } else {
                                    R.drawable.passwordeye
                                }
                            ),
                            contentDescription = "Icon Password",
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                }
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = modifier
                    .fillMaxWidth()
            ){
                TextButton(
                    onClick = {
                        navController.navigate(Screen.ForgotPassword.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Text(
                        text = "Lupa Kata Sandi",
                        fontFamily = intersemibold,
                        color = Color(0xFF185936),
                        fontSize = 15.sp
                    )
                }

            }
            CustomButton(
                onClickedAction = {
                    coroutineScope.launch {
                        if (email.isBlank() || password.isBlank()) {
                            Toast.makeText(context, "Email dan Password harus diisi", Toast.LENGTH_SHORT).show()
                        } else {
                            viewModel.loginUser(email, password) { success ->
                                if (success) {
                                    coroutineScope.launch {
                                        dataStore.saveStatus(true)
                                        sharedPreferencesManager.email = email
                                        sharedPreferencesManager.password = password
                                    }
                                    navController.navigate(Screen.Beranda.route){
                                        popUpTo(Screen.Login.route){
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    Toast.makeText(context,"Email atau Password salah", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    }
            }
                , title = "Masuk",
                modifier = modifier
                    .size(346.dp, 55.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
            ){
                Text(
                    text = "Belum punya akun ?",
                    fontSize = 14.sp,
                    fontFamily = intermedium,
                    color = Color(0xFF757575)
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Register.route) {
                            popUpTo(Screen.Login.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = modifier
                ) {
                    Text(
                        text = "Daftar",
                        fontFamily = intersemibold,
                        fontSize = 14.sp,
                        color = Color(0xFF175B36)
                    )
                }
            }
        }
    }
}