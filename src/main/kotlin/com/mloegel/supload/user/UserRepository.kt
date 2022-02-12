package com.mloegel.supload.user

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
    fun findByUserid(userid: Int): User

    fun findByUsername(username: String): User

    fun findByUsernameContainingIgnoreCase(Username: String): List<User>
}