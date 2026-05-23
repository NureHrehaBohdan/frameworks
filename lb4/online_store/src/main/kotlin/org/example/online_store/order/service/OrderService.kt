package org.example.online_store.order.service

import org.example.online_store.order.Order
import org.example.online_store.order.repo.OrderRepository
import org.example.online_store.product.repo.ProductRepository
import org.example.online_store.user.repo.UserRepository
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {

    fun getAll(): List<Order> = orderRepository.findAll()

    fun getById(id: Long): Order = orderRepository.findById(id).orElseThrow {
        RuntimeException("Order not found")
    }

    fun create(userId: Long, productIds: List<Long>): Order {
        val user = userRepository.findById(userId)
            .orElseThrow { RuntimeException("User not found") }

        val products = productRepository.findAllById(productIds)

        val total = products.sumOf { it.price }

        val order = Order(
            user = user, products = products, totalPrice = total
        )
        return orderRepository.save(order)
    }

    fun update(id: Long, productIds: List<Long>): Order {
        val order = getById(id)

        val products = productRepository.findAllById(productIds)

        order.products = products
        order.totalPrice = products.sumOf { it.price }

        return orderRepository.save(order)
    }

    fun delete(id: Long) {
        orderRepository.deleteById(id)
    }
}