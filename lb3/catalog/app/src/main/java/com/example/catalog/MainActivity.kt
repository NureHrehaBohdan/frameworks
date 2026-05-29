package com.example.catalog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.catalog.ui.CatalogViewModel
import com.example.catalog.ui.screens.CourseDetailsScreen
import com.example.catalog.ui.screens.CoursesScreen
import com.example.catalog.ui.theme.CatalogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogTheme {
                val navController = rememberNavController()
                val viewModel: CatalogViewModel = viewModel()
                val courses by viewModel.courses.collectAsState()

                NavHost(navController = navController, startDestination = "courses") {
                    composable("courses") {
                        CoursesScreen(
                            courses = courses,
                            onCourseClick = { courseId ->
                                navController.navigate("details/$courseId")
                            }
                        )
                    }
                    composable("details/{courseId}") { backStackEntry ->
                        val courseId = backStackEntry.arguments?.getString("courseId")?.toIntOrNull() ?: 0
                        
                        androidx.compose.runtime.LaunchedEffect(courseId) {
                            viewModel.loadCourseDetails(courseId)
                        }
                        
                        val course by viewModel.selectedCourse.collectAsState()

                        CourseDetailsScreen(
                            course = course,
                            onBackClick = { navController.popBackStack() },
                            onAddReview = { rating, comment ->
                                viewModel.addReview(courseId, rating, comment)
                            },
                            onEnroll = { phone, email ->
                                viewModel.enroll(courseId, phone, email)
                            }
                        )
                    }
                }
            }
        }
    }
}
