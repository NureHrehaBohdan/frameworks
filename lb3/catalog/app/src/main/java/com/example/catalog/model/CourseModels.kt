package com.example.catalog.model

import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val teacher: String,
    val duration: String,
    val avgRating: Double,
    val Reviews: List<Review> = emptyList()
)

@Serializable
data class Review(
    val id: Int = 0,
    val rating: Int,
    val comment: String
)

@Serializable
data class Enrollment(
    val phone: String,
    val email: String
)
