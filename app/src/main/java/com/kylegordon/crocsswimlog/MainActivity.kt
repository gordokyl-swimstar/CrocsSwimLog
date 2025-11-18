package com.kylegordon.crocsswimlog

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults.contentPadding
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kylegordon.crocsswimlog.ui.theme.CrocsSwimLogTheme
import kotlinx.coroutines.launch
import coil.compose.AsyncImage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            CrocsSwimLogTheme {
                CrocsSwimLogApp()
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Composable
fun CrocsSwimLogApp() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val bottomBarModifier = Modifier.size(45.dp)

            NavigationBar {
                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("main") },
                    icon = {
                        Image(
                            modifier = bottomBarModifier,
                            painter = painterResource(R.drawable.csl_home_icon),
                            contentDescription = "Home Screen"
                        )
                    })

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("swim_log") },
                    icon = {
                        Image(
                            modifier = bottomBarModifier,
                            painter = painterResource(R.drawable.csl_swim_log_icon),
                            contentDescription = "Swim Log"
                        )
                    })

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("new_workout_entry") },
                    icon = {
                        Image(
                            modifier = bottomBarModifier,
                            painter = painterResource(R.drawable.csl_new_entry_icon),
                            contentDescription = "New Swim Log Entry"
                        )
                    })

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("dotd") },
                    icon = {
                        Image(
                            modifier = bottomBarModifier,
                            painter = painterResource(R.drawable.csl_dotd_icon),
                            contentDescription = "Drill Of The Day"
                        )
                    })

                NavigationBarItem(
                    selected = false,
                    onClick = { navController.navigate("camera") },
                    icon = {
                        Image(
                            modifier = bottomBarModifier,
                            painter = painterResource(R.drawable.csl_camera_icon),
                            contentDescription = "camera"
                        )
                    })

            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "main",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("main") { MainScreen(navController, viewModel = MainScreenViewModel()) }
            composable("swim_log") { SwimLogScreen(navController, viewModel = SwimLogViewModel()) }
            composable("new_workout_entry") {
                WorkoutEntryScreen(
                    navController,
                    viewModel = WorkoutEntryViewModel()
                )
            }
            composable("dotd") { DOTDScreen(navController, viewModel = DOTDViewModel()) }
            composable("camera") { CameraScreen(navController, viewModel = AppViewModel(app = Application())) }
        }
    }
}

