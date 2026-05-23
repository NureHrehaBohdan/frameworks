package org.example.online_store.user.service

import org.example.online_store.user.User
import org.springframework.stereotype.Service


import jakarta.persistence.EntityManager
import jakarta.transaction.Transactional


@Service
class UserService(
    private val entityManager: EntityManager
) {

    @Transactional
    fun create(user: User): User {
        entityManager.persist(user)
        return user
    }

    fun getAll(): List<User> {
        return entityManager
            .createQuery("SELECT u FROM User u", User::class.java)
            .resultList
    }

    fun getById(id: Long): User {
        return entityManager.find(User::class.java, id)
            ?: throw RuntimeException("User not found")
    }

    @Transactional
    fun update(id: Long, newUser: User): User {
        val user = getById(id)

        user.name = newUser.name
        user.email = newUser.email

        return entityManager.merge(user)
    }

    @Transactional
    fun delete(id: Long) {
        val user = getById(id)
        entityManager.remove(user)
    }
}