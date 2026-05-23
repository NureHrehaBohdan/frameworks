package org.example.online_store.product.service

import org.example.online_store.category.repo.CategoryRepository
import org.example.online_store.product.Product
import org.example.online_store.product.repo.ProductRepository
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val categoryRepository: CategoryRepository
) {

    fun getAll(): List<Product> =
        productRepository.findAll()

    fun getById(id: Long): Product =
        productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }

    fun create(product: Product, categoryId: Long): Product {
        val category = categoryRepository.findById(categoryId)
            .orElseThrow { RuntimeException("Category not found") }

        product.category = category

        return productRepository.save(product)
    }

    fun update(id: Long, newProduct: Product, categoryId: Long): Product {
        val product = getById(id)

        val category = categoryRepository.findById(categoryId)
            .orElseThrow { RuntimeException("Category not found") }

        product.name = newProduct.name
        product.price = newProduct.price
        product.description = newProduct.description
        product.category = category

        return productRepository.save(product)
    }

    fun delete(id: Long) {
        productRepository.deleteById(id)
    }
}