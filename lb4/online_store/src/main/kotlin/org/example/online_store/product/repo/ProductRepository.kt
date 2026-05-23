package org.example.online_store.product.repo

import org.example.online_store.product.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>