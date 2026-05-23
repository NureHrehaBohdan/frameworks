package org.example.online_store.user.repo

import org.example.online_store.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>