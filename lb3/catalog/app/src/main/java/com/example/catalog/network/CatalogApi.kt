package com.example.catalog.network

import com.example.catalog.model.Course
import com.example.catalog.model.Enrollment
import com.example.catalog.model.Review
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.*

interface CatalogApi {
    @GET("courses")
    suspend fun getCourses(): List<Course>

    @GET("courses/{id}")
    suspend fun getCourse(@Path("id") id: Int): Course

    @POST("courses/{id}/reviews")
    suspend fun addReview(@Path("id") id: Int, @Body review: Review): Review

    @POST("courses/{id}/enroll")
    suspend fun enroll(@Path("id") id: Int, @Body enrollment: Enrollment): Enrollment

    companion object {
        private const val BASE_URL = "http://10.0.2.2:5000/"

        fun create(): CatalogApi {
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            val json = Json { 
                ignoreUnknownKeys = true 
                coerceInputValues = true
            }

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
                .build()
                .create(CatalogApi::class.java)
        }
    }
}
