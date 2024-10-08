package com.projects.agrilembang.ui.Components.Card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.agrilembang.R
import com.projects.agrilembang.ui.theme.intermedium
import com.projects.agrilembang.ui.theme.interregular
import com.projects.agrilembang.ui.theme.intersemibold

@Composable
fun CustomCard(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier
            .size(300.dp, 250.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(Color(0xFFFFFFFF)),
        border = BorderStroke(2.dp, Color(0xFFC4C4C4))
    ){
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(18.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(painter = painterResource(id = R.drawable.greenhouse),
                contentDescription = "Greenhouse",
                modifier
                    .size(250.dp, 150.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = modifier
                    .fillMaxWidth()
            ){
                Column(
                    verticalArrangement = Arrangement.spacedBy(5.dp),
                ){
                    Text(
                        text = "Greenhouse 1",
                        fontSize = 18.sp,
                        fontFamily = intersemibold,
                        color = Color.Black
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(3.dp),
                    ){
                        Image(painter = painterResource(id = R.drawable.location2),
                            contentDescription = "Location 2",
                            modifier
                                .size(12.dp, 15.dp)
                        )
                        Text(
                            text = "Lembang, Jawa Barat",
                            fontSize = 11.sp,
                            fontFamily = interregular,
                            color = Color.Black
                        )
                    }
                }
                Box(
                    modifier = modifier
                        .size(101.dp, 35.dp)
                        .background(Color(0xFF91c2a8), RoundedCornerShape(10.dp)),
                    contentAlignment = Alignment.Center
                ){
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ){
                        Text(
                            text = "70",
                            fontSize = 12.sp,
                            fontFamily = intersemibold,
                            color = Color(0xFF155B36)
                        )
                        Text(
                            text = "Tanaman",
                            fontSize = 12.sp,
                            fontFamily = intermedium,
                            color = Color(0xFF155B36)
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun Preview() {
    CustomCard()
}