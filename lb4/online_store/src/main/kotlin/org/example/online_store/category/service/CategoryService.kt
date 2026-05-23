package org.example.online_store.category.service

import org.example.online_store.category.Category
import org.example.online_store.category.repo.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(
    private val categoryRepository: CategoryRepository
) {

    fun getAll(): List<Category> =
        categoryRepository.findAll()

    fun getById(id: Long): Category =
        categoryRepository.findById(id)
            .orElseThrow { RuntimeException("Category not found") }

    fun create(category: Category): Category =
        categoryRepository.save(category)

    fun update(id: Long, newCategory: Category): Category {
        val category = getById(id)

        category.name = newCategory.name

        return categoryRepository.save(category)
    }

    fun delete(id: Long) {
        categoryRepository.deleteById(id)
    }
}