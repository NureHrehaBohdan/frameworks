package org.example.online_store.category.repo

import org.example.online_store.category.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<Category, Long> {
}