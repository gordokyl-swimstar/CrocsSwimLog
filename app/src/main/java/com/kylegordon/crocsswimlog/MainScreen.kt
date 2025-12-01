package com.kylegordon.crocsswimlog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: MainScreenViewModel) {
    val colorList = listOf(Color.Cyan, Color.Blue)
    val analytics by viewModel.analytics.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Brush.verticalGradient(colorList)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Image(
            painter = painterResource(id = R.drawable.csl_logo),
            contentDescription = null,
            modifier = Modifier.padding(16.dp)
        )

        Text("Created by Kyle and Nate", color = Color.White)

        Text("Become stronger than the day before", color = Color.White, fontSize = 18.sp)

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            "Analytics Dashboard",
            modifier = Modifier.padding(16.dp),
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Text(
                    text = "Workouts Logged: ${analytics.totalWorkouts}",
                    modifier = Modifier.padding(1.dp),
                    fontSize = 18.sp
                )
                Text(
                    text = "Average Yardage: ${analytics.avgYardage}",
                    modifier = Modifier.padding(1.dp),
                    fontSize = 18.sp
                )
                Text(
                    text = "Average Duration: ${analytics.avgDuration}",
                    modifier = Modifier.padding(1.dp),
                    fontSize = 18.sp
                )
                Text(
                    text = "Most Common Stroke: ${analytics.mcstroke}",
                    modifier = Modifier.padding(1.dp),
                    fontSize = 18.sp
                )
            }
        }

    }
}