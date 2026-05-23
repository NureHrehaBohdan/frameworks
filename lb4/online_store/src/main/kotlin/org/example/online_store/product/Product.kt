package org.example.online_store.product

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.example.online_store.category.Category
import org.example.online_store.review.Review

@Entity
@Table(name = "products")
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String,
    var price: Double,
    var description: String,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category,

    @OneToMany(mappedBy = "product", cascade = [CascadeType.ALL])
    val reviews: List<Review> = emptyList()
)