package com.kylegordon.crocsswimlog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    val analytics = viewModel.analytics

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
        Text("This Will Be Inspirational Quote Pulled From API", color = Color.White)

        Spacer(modifier = Modifier.height(15.dp))

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
            items(analytics) { analytics ->
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