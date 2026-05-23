package org.example.online_store.review.service

import org.example.online_store.product.repo.ProductRepository
import org.example.online_store.review.Review
import org.example.online_store.review.repo.ReviewRepository
import org.example.online_store.user.repo.UserRepository
import org.springframework.stereotype.Service

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {

    fun getAll(): List<Review> =
        reviewRepository.findAll()

    fun getById(id: Long): Review =
        reviewRepository.findById(id)
            .orElseThrow { RuntimeException("Review not found") }

    fun create(userId: Long, productId: Long, review: Review): Review {

        val user = userRepository.findById(userId)
            .orElseThrow { RuntimeException("User not found") }

        val product = productRepository.findById(productId)
            .orElseThrow { RuntimeException("Product not found") }

        review.user = user
        review.product = product

        return reviewRepository.save(review)
    }

    fun update(id: Long, newReview: Review): Review {
        val review = getById(id)

        review.rating = newReview.rating
        review.comment = newReview.comment

        return reviewRepository.save(review)
    }

    fun delete(id: Long) {
        reviewRepository.deleteById(id)
    }
}