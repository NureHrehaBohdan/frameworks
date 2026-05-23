package org.example.online_store.order.repo

import org.example.online_store.order.Order
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long>