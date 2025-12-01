package com.kylegordon.crocsswimlog

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kylegordon.crocsswimlog.data.SwimLogDatabase
import com.kylegordon.crocsswimlog.ui.theme.CrocsSwimLogTheme

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
    val context = LocalContext.current
    val dao = SwimLogDatabase.getDatabase(context).swimLogDao()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val bottomBarModifier = Modifier.size(45.dp)

            NavigationBar {
                NavigationBarItem(selected = false, onClick = {navController.navigate("main")}, icon = {
                    Image(
                        modifier = bottomBarModifier,
                        painter = painterResource(R.drawable.csl_home_icon),
                        contentDescription = "Home Screen"
                    )
                })

                NavigationBarItem(selected = false, onClick = {navController.navigate("swim_log")}, icon = {
                    Image(
                        modifier = bottomBarModifier,
                        painter = painterResource(R.drawable.csl_swim_log_icon),
                        contentDescription = "Swim Log"
                    )
                })

                NavigationBarItem(selected = false, onClick = {navController.navigate("new_workout_entry")}, icon = {
                    Image(
                        modifier = bottomBarModifier,
                        painter = painterResource(R.drawable.csl_new_entry_icon),
                        contentDescription = "New Swim Log Entry"
                    )
                })

                NavigationBarItem(selected = false, onClick = {navController.navigate("dotd")}, icon = {
                    Image(
                        modifier = bottomBarModifier,
                        painter = painterResource(R.drawable.csl_dotd_icon),
                        contentDescription = "Drill Of The Day"
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
            // Main Screen with Analytics
            composable("main") {
                val mainViewModel: MainScreenViewModel = viewModel(
                    factory = MainScreenViewModelFactory(dao)
                )
                MainScreen(navController, viewModel = mainViewModel)
            }

            // Swim Log Screen
            composable("swim_log") {
                val swimLogViewModel: SwimLogViewModel = viewModel(
                    factory = SwimLogViewModelFactory(dao)
                )
                SwimLogScreen(navController, viewModel = swimLogViewModel)
            }

            // New Workout Entry Screen
            composable("new_workout_entry") {
                val workoutEntryViewModel: WorkoutEntryViewModel = viewModel(
                    factory = WorkoutEntryViewModelFactory(dao)
                )
                WorkoutEntryScreen(navController, viewModel = workoutEntryViewModel)
            }

            // Drill of the Day Screen
            composable("dotd") { DOTDScreen(navController, viewModel = DOTDViewModel()) }

            // Camera Screen
            composable("camera") {
                val context = LocalContext.current
                val cameraVm: CameraViewModel = viewModel(
                    factory = CameraViewModelFactory(context.applicationContext as Application)
                )

                CameraScreen(
                    navController = navController,
                    viewModel = cameraVm
                )
            }
        }
    }
}
