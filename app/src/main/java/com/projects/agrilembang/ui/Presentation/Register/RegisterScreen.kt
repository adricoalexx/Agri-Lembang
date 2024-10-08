package com.projects.agrilembang.ui.Presentation.Register

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import com.google.firebase.auth.FirebaseAuth
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.LoginViewModel
import com.projects.agrilembang.ui.Components.Button.CustomButton
import com.projects.agrilembang.ui.Components.TextField.CustomTextField
import com.projects.agrilembang.ui.theme.intermedium
import com.projects.agrilembang.ui.theme.intersemibold
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel : LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isShowed by remember { mutableStateOf(false ) }
    var isShowed2 by remember { mutableStateOf(false) }
    var verifypassword by remember { mutableStateOf("") }
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState(initial = null)
    Column(
        modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Daftar",
            fontSize = 24.sp,
            fontFamily = intersemibold,
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
                text = "Alamat Email",
                fontSize = 16.sp,
                fontFamily = intermedium
            )
            CustomTextField(
                value = email,
                onValueChange = {
                    email = it
                },
                placeholder = "Masukkan alamat email anda",
                isPassword = false,
                modifier = modifier
                    .size(347.dp, 55.dp)
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
                placeholder = "Masukkan kata sandi anda",
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
            Text(
                text = "Konfirmasi Kata Sandi",
                fontSize = 16.sp,
                fontFamily = intermedium
            )
            CustomTextField(
                value = verifypassword,
                onValueChange = {
                    verifypassword = it
                },
                placeholder = "Konfirmasi kata sandi anda",
                isPassword = !isShowed2,
                modifier = modifier
                    .size(347.dp, 55.dp),
                trailingIcon = {
                    IconButton(
                        onClick = {
                            isShowed2 = !isShowed2
                        }
                    ) {
                        Image(
                            painter = painterResource(
                                if (isShowed2){
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
            Spacer(modifier.height(5.dp))
            CustomButton(
                onClickedAction = {
                    coroutineScope.launch {
                        when {
                            email.isBlank() || password.isBlank() || verifypassword.isBlank() -> {
                                Toast.makeText(context, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
                            }
                            password.length < 7 -> {
                                Toast.makeText(context, "Kata sandi minimal 7 karakter", Toast.LENGTH_SHORT).show()
                            }
                            password != verifypassword -> {
                                Toast.makeText(context, "Kata sandi tidak sama", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
                                viewModel.registerUser(email, password) { success ->
                                    if (success) {
                                        FirebaseAuth.getInstance().signOut()
                                        Toast.makeText(context, "Daftar berhasil", Toast.LENGTH_SHORT).show()
                                        navController.navigate(Screen.Login.route) {
                                            popUpTo(Screen.Register.route) {
                                                inclusive = true
                                            }
                                        }
                                    } else {
                                        Toast.makeText(context, "Pendaftaran gagal, coba lagi", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                email = ""
                                password = ""
                                verifypassword = ""
                            }
                        }
                    }
                },
            title = "Daftar",
                modifier = modifier
                    .size(346.dp, 55.dp)
            )
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sudah punya akun ?",
                    fontSize = 14.sp,
                    fontFamily = intermedium,
                    color = Color(0xFF757575)
                )
                TextButton(
                    onClick = {
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Register.route) {
                                inclusive = true
                            }
                        }
                    },
                    modifier = modifier
                ) {
                    Text(
                        text = "Masuk",
                        fontFamily = intersemibold,
                        fontSize = 14.sp,
                        color = Color(0xFF175B36)
                    )
                }

            }
        }
    }
}
