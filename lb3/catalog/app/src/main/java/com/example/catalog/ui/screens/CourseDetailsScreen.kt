package com.example.catalog.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.catalog.model.Course
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailsScreen(
    course: Course?,
    onBackClick: () -> Unit,
    onAddReview: (Int, String) -> Unit,
    onEnroll: (String, String) -> Unit
) {
    if (course == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(course.title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Header Section
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = course.description, style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Teacher: ${course.teacher}")
                    Text("Duration: ${course.duration}")
                    Text("Rating: ${String.format(Locale.US, "%.1f", course.avgRating)}")
                }
            }

            // Reviews Section
            Text("Reviews", style = MaterialTheme.typography.headlineSmall)
            course.Reviews.forEach { review ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Text("${review.rating} / 5", fontWeight = FontWeight.Bold)
                        Text(review.comment)
                    }
                }
            }

            // Add Review Form
            Text("Add review", style = MaterialTheme.typography.headlineSmall)
            var rating by remember { mutableStateOf("5") }
            var comment by remember { mutableStateOf("") }

            OutlinedTextField(
                value = rating,
                onValueChange = { rating = it },
                label = { Text("Rating (1-5)") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = comment,
                onValueChange = { comment = it },
                label = { Text("Comment") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )
            Button(
                onClick = {
                    val r = rating.toIntOrNull() ?: 5
                    onAddReview(r.coerceIn(1, 5), comment)
                    comment = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send review")
            }

            // Enroll Form
            Text("Enroll", style = MaterialTheme.typography.headlineSmall)
            var phone by remember { mutableStateOf("") }
            var email by remember { mutableStateOf("") }

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
            )
            Button(
                onClick = {
                    onEnroll(phone, email)
                    phone = ""
                    email = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enroll")
            }
            
            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}
