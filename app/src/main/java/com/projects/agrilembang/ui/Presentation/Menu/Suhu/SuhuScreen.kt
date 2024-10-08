package com.projects.agrilembang.ui.Presentation.Menu.Suhu

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.projects.agrilembang.R
import com.projects.agrilembang.ui.theme.intersemibold

@Composable
fun SuhuScreen(
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 30.dp, start = 25.dp, end = 25.dp)
    ){
        Text(
            text = "Suhu",
            fontSize = 20.sp,
            fontFamily = intersemibold
        )
            Card(
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(5.dp),
                border = CardDefaults.outlinedCardBorder(true),
                modifier = Modifier
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        fontSize = 17.sp,
                        fontFamily = intersemibold,
                    )
                    Text(
                        fontSize = 12.sp,
                        fontFamily = intersemibold,
                        color = Color.Gray
                    )
                }
            }
    }
}

