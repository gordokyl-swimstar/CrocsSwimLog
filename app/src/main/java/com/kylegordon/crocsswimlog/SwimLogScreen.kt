package com.kylegordon.crocsswimlog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SwimLogScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: SwimLogViewModel
) {
    val colorList = listOf(Color.Cyan, Color.Blue)
    val entries = viewModel.entries.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Brush.verticalGradient(colorList)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            "Swim Log",
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            items(entries) { entry ->
                Text(
                    text = "${entry.dow}, ${entry.workoutLength} min, ${entry.mainStroke}, ${entry.totalYardage} yards",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { viewModel.deleteEntry(entry) }
                )
            }
        }
    }
}
