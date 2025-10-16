package com.kylegordon.crocsswimlog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WorkoutEntryScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: WorkoutEntryViewModel) {
    val workoutDate by viewModel.workoutDate.collectAsState()
    val duration by viewModel.duration.collectAsState()
    val mainStroke by viewModel.mainStroke.collectAsState()
    val totalYardage by viewModel.totalYardage.collectAsState()

    val colorList = listOf(Color.Cyan, Color.Blue)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Brush.verticalGradient(colorList)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text("New Swim Log Entry",
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = modifier
                .background(Color.White)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ){
            // Date Of Workout
            OutlinedTextField(
                value = workoutDate,
                onValueChange = { viewModel.setWorkoutDate(it) },
                placeholder = { Text("Enter Date of Workout", color = Color.Black) },
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                modifier = modifier.padding(10.dp)
            )

            // Duration of Workout
            OutlinedTextField(
                value = duration,
                onValueChange = { viewModel.setWorkoutDuration(it) },
                placeholder = { Text("Enter Duration of Workout", color = Color.Black) },
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                modifier = modifier.padding(10.dp)
            )

            // Main Stroke Swam
            OutlinedTextField(
                value = mainStroke,
                onValueChange = { viewModel.setMainStroke(it) },
                placeholder = { Text("Enter Main Stroke Swam", color = Color.Black) },
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                modifier = modifier.padding(10.dp)
            )

            // Total Yardage
            OutlinedTextField(
                value = totalYardage,
                onValueChange = { viewModel.setTotalYardage(it) },
                placeholder = { Text("Enter Total Yardage", color = Color.Black) },
                textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                modifier = modifier.padding(10.dp)
            )
        }

        Button(onClick = { /*TODO*/ }) {
            Text("Submit",
            color = Color.White)
        }

    }

}