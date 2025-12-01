package com.kylegordon.crocsswimlog

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage

//@OptIn(ExperimentalPermissionsAPI::class)
@Composable
fun CameraScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: CameraViewModel
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val imgUri by viewModel.imageFileUri.collectAsState()
    var captureUri by remember { mutableStateOf<Uri?>(null) }

    val cameraLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            captureUri = imgUri

            // Return result to previous screen
            navController.previousBackStackEntry
                ?.savedStateHandle
                ?.set("capturedPhoto", captureUri.toString())

            navController.popBackStack()  // go back to WorkoutEntryScreen
        } else {
            viewModel.removeImageFile()
        }
    }
    val colorList = listOf(Color.Cyan, Color.Blue)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Brush.verticalGradient(colorList)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = {
            val fileUri = viewModel.setupImageUri()

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            }

            cameraLauncher.launch(intent)
        }) {
            Text("Capture Image")
        }

        if (captureUri != null) {
            AsyncImage(model = captureUri, contentDescription = "Captured photo")
        }
    }
}
