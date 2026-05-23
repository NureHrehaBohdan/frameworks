package org.example.online_store.user

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.example.online_store.order.Order
import org.example.online_store.review.Review

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String,
    var email: String,

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val orders: List<Order> = emptyList(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    val reviews: List<Review> = emptyList()
)