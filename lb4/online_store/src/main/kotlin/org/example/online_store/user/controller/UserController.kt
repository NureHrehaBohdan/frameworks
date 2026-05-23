package org.example.online_store.user.controller

import org.example.online_store.user.User
import org.example.online_store.user.service.UserService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping( "/user")
class UserController (
    private val userService: UserService
){

    @PostMapping()
    fun createUser(@RequestBody user: User): User {
        return userService.create(user)
    }

    @GetMapping
    fun getAllUsers(): List<User> {
        return userService.getAll()
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): User {
        return userService.getById(id)
    }

    @PutMapping("/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody user: User
    ): User {
        return userService.update(id, user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.delete(id)
    }
}