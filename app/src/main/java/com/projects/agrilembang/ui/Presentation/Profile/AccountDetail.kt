package com.projects.agrilembang.ui.Presentation.Profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.projects.agrilembang.R
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.ui.Components.Button.CustomButton
import com.projects.agrilembang.ui.Components.TextField.CustomTextField
import com.projects.agrilembang.ui.theme.intersemibold
import com.projects.agrilembang.ui.theme.poppinsmedium

@Composable
fun AccountDetail(
    navController: NavController
) {
    var namapengguna by remember { mutableStateOf("Adrico Alexander") }
    var namalengkap by remember { mutableStateOf("Adrico Alexander") }
    var email by remember { mutableStateOf("adrico.alexander@gmail.com") }
    var nomortelepon by remember { mutableStateOf("08515621726") }
    var katasandi by remember { mutableStateOf("adricoalexx") }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 25.dp, end = 25.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            IconButton(
                modifier = Modifier
                    .size(18.dp),
                onClick = {
                    navController.navigate(Screen.Profile.route){
                        popUpTo(Screen.ProfileDetail.route){
                            inclusive = true
                        }
                    }
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.backbutton),
                    contentDescription = ""
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(
                    "Akun Saya",
                    fontSize = 20.sp,
                    fontFamily = intersemibold
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp)
        )
        Text(
            "Nama Lengkap",
            fontSize = 14.sp,
            fontFamily = poppinsmedium
        )
        CustomTextField(
            value = namalengkap,
            onValueChange = {
                namalengkap= it
            },
            placeholder = "Nama Lengkap",
            isPassword = false,
            modifier = Modifier
                .size(346.dp, 55.dp)
        )
        Text(
            "Nama Pengguna",
            fontSize = 14.sp,
            fontFamily = poppinsmedium
        )
        CustomTextField(
            value = namapengguna,
            onValueChange = {
                namapengguna = it
            },
            placeholder = "Nama Pengguna",
            isPassword = false,
            modifier = Modifier
                .size(346.dp, 55.dp)
        )
        Text(
            "Email",
            fontSize = 14.sp,
            fontFamily = poppinsmedium
        )
        CustomTextField(
            value = email,
            onValueChange = {
                email = it
            },
            placeholder = "Email",
            isPassword = false,
            modifier = Modifier
                .size(346.dp, 55.dp)
        )
        Text(
            "Nomor Telepon",
            fontSize = 14.sp,
            fontFamily = poppinsmedium
        )
        CustomTextField(
            value = nomortelepon,
            onValueChange = {
                nomortelepon = it
            },
            placeholder = "Nomor Telepon",
            isPassword = false,
            modifier = Modifier
                .size(346.dp, 55.dp)
        )
        Text(
            "Kata Sandi",
            fontSize = 14.sp,
            fontFamily = poppinsmedium
        )
        CustomTextField(
            value = katasandi,
            onValueChange = {
                katasandi = it
            },
            placeholder = "Kata Sandi",
            isPassword = true,
            modifier = Modifier
                .size(346.dp, 55.dp)
        )

        Spacer(modifier = Modifier.height(100.dp)
        )
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            title = "Edit",
            onClickedAction = {
                Toast.makeText(context, "Berhasil Edit", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

