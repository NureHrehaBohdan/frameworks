package org.example.online_store.review.repo

import org.example.online_store.review.Review
import org.springframework.data.jpa.repository.JpaRepository

interface ReviewRepository : JpaRepository<Review, Long>