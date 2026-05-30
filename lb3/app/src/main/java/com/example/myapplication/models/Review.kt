package com.example.myapplication.models

data class Review(
    val id: Int,
    val rating: Int,
    val comment: String,
    val courseId: Int
)