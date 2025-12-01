package com.kylegordon.crocsswimlog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.kylegordon.crocsswimlog.data.SwimLogEntry

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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(entries) { entry ->
                SwimLogItem(entry = entry) {
                    viewModel.deleteEntry(entry)
                }
            }
        }
    }
}

@Composable
fun SwimLogItem(
    entry: SwimLogEntry,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // ----- IMAGE DISPLAY -----
        AsyncImage(
            model = entry.photoLog,         // stored URI or filepath
            contentDescription = "Workout Photo",
            modifier = Modifier
                .size(80.dp)
                .padding(end = 16.dp),
        contentScale = ContentScale.Crop
        )

        // ----- LOG TEXT -----
        Column {
            Text(
                text = entry.dow.toString(),
                fontWeight = FontWeight.Bold
            )
            Text("${entry.workoutLength} min")
            Text("Stroke: ${entry.mainStroke}")
            Text("Yardage: ${entry.totalYardage}")
        }
    }
}
