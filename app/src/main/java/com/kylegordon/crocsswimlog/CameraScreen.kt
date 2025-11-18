package com.kylegordon.crocsswimlog

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

    //@OptIn(ExperimentalPermissionsAPI::class)
    @Composable
    fun CameraScreen(navController : NavController, modifier: Modifier = Modifier, viewModel: CameraViewModel) {
        val context = LocalContext.current
        val scope = rememberCoroutineScope()
        val imgUri by viewModel.imageFileUri.collectAsState()
        var captureUri by remember { mutableStateOf<Uri?>(null) }
        val arContract = ActivityResultContracts.StartActivityForResult()
        val cameraLauncher = rememberLauncherForActivityResult(arContract) { result ->
            if (result.resultCode == RESULT_OK) {
                captureUri = imgUri
                println("Image at ${captureUri}")
            } else {
                viewModel.removeImageFile()
                scope.launch {
                    SnackbarController.sendMessage("No image is saved")
                }
            }
        }
        Column(modifier = modifier.padding(start = 8.dp)) {
            Text("Button closes app for some reason")
            LazyVerticalGrid(
                columns = GridCells.Fixed(
                    count = 2
                ),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Button(onClick = {
                        val fileUri = viewModel.setupImageUri()
                        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply {
                            putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
                        }
                        cameraLauncher.launch(cameraIntent)
                    }) { Text("Capture Image") }
                }
            }
            if (captureUri != null) {
                AsyncImage(
                    model = captureUri, contentDescription = null
                )
            }
        }
    }
