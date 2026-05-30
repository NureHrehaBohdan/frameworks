package com.example.myapplication.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Course(
    val id: Int,
    val title: String,
    val description: String,
    val teacher: String,
    val duration: String,
    val avgRating: Double,

    @SerialName("Reviews")
    val reviews: List<Review> = emptyList()
)