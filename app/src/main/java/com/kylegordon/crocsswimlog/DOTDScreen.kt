package com.kylegordon.crocsswimlog

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
fun DOTDScreen(navController: NavController, modifier: Modifier = Modifier, viewModel: DOTDViewModel) {
    val imageModifier = Modifier
        .size(250.dp)
        .border(BorderStroke(3.dp, Color.Black))

    val colorList = listOf(Color.Cyan, Color.Blue)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Brush.verticalGradient(colorList)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        Text("Drill Of The Day",
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        // This will pull from a database of drills randomly. The title goes here
        Text("Laneline Drill",
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 20.sp
        )

        // This image will change depending on the drill of the day, matching the stroke of the drill
        Image(
            painter = painterResource(id = R.drawable.backstroke_dotd),
            contentDescription = null,
            modifier = imageModifier
        )

        // This will pull from a database of drills randomly. The description goes here
        Text("The swimmer uses the arm closest to the laneline to swim backstroke. When they " +
                "pull, rather than just the hand, the swimmer uses the laneline to boost their " +
                "backstroke while turning their shoulder to touch their chin working on rotation, " +
                "stability, and power.",
            modifier = Modifier.padding(16.dp),
            color = Color.White,
            fontSize = 16.sp,
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        // This will pull from a database of drills randomly. The recommended workout goes here
        Text(
            "Recommended Workout: 8 x 25 on the 30, on the 4th 25 swim regular Backstroke",
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp),
            textAlign = androidx.compose.ui.text.style.TextAlign.Center
        )

        Button(onClick = { }) {
            Text("Generate New Drill")
        }

    }
}