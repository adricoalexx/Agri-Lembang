package com.projects.agrilembang.ui.Presentation.Profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.projects.agrilembang.R
import com.projects.agrilembang.firebase.LoginState
import com.projects.agrilembang.firebase.LoginViewModel
import com.projects.agrilembang.navigation.Screen
import com.projects.agrilembang.ui.theme.intermedium
import com.projects.agrilembang.ui.theme.intersemibold

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
    ) {
    var isChecked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val state by viewModel.state.collectAsState(initial = LoginState())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 20.dp, end = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            "Akun",
            fontSize = 20.sp,
            fontFamily = intersemibold
        )

        Image(painter = painterResource(R.drawable.userpic),
            contentDescription = "",
            modifier = Modifier
                .size(80.dp)
        )
        Text(
            "Adrico Alexander",
            fontSize = 24.sp,
            fontFamily = intersemibold
        )
        Spacer(modifier = Modifier.height(15.dp)
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color(0xFFFBFBFB),
                    RoundedCornerShape(20.dp)
                )
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(7.dp)
            ){
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate(Screen.ProfileDetail.route){
                                popUpTo(Screen.Profile.route){
                                    inclusive = true
                                }
                            }
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                    ){
                        Image(
                            painter = painterResource(R.drawable.profileicon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text(
                            "Akun Saya",
                            fontFamily = intermedium,
                            fontSize = 16.sp
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.forwardicon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(14.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .background(Color(0xFF979797))
                        .fillMaxWidth()
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                    ){
                        Image(
                            painter = painterResource(R.drawable.settingsicon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text(
                            "Pengaturan",
                            fontFamily = intermedium,
                            fontSize = 16.sp
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.forwardicon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(14.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .background(Color(0xFF979797))
                        .fillMaxWidth()
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                    ){
                        Image(
                            painter = painterResource(R.drawable.notificationicon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text(
                            "Notifikasi",
                            fontFamily = intermedium,
                            fontSize = 16.sp
                        )
                    }
                    Switch(
                        checked = isChecked,
                        onCheckedChange = {
                            isChecked = it
                        },
                        colors = SwitchDefaults.colors(
                            checkedIconColor = Color.Gray,
                            checkedTrackColor = Color(0xFF155B36),
                            checkedThumbColor = Color.White,
                            checkedBorderColor = Color(0xFF155B36),
                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = Color.LightGray,
                            uncheckedIconColor = Color.LightGray,
                            uncheckedBorderColor = Color.White,
                        )
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .background(Color(0xFF979797))
                        .fillMaxWidth()
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                    ){
                        Image(
                            painter = painterResource(R.drawable.cellphoneicon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text(
                            "Hubungi Kami",
                            fontFamily = intermedium,
                            fontSize = 16.sp
                        )
                    }
                    Image(
                        painter = painterResource(R.drawable.forwardicon),
                        contentDescription = "",
                        modifier = Modifier
                            .size(14.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .background(Color(0xFF979797))
                        .fillMaxWidth()
                )
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.logoutUser { success ->
                                if (success){
                                    Toast.makeText(context, "Logout Berhasil !", Toast.LENGTH_SHORT).show()
                                    navController.navigate(Screen.Login.route){
                                        popUpTo(Screen.ProfileDetail.route){
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    Toast.makeText(context, "Logout error", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(11.dp),
                    ){
                        Image(
                            painter = painterResource(R.drawable.quiticon),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                        )
                        Text(
                            "Keluar",
                            fontFamily = intermedium,
                            fontSize = 16.sp,
                            color = Color(0xFFD40000)
                        )
                    }
                }
                if (state.loading){
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                state.success?.let {
                    Text(it, color = Color.Green)
                }
                state.error?.let {
                    Text(it, color = Color.Red)
                }
            }
        }
    }
}
