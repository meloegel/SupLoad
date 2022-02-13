package com.mloegel.supload.user

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody

data class Login(
    val username: String,
    val password: String
)

class UserController(val service: UserService) {
    @GetMapping("/users")
    fun getAllUsers(): MutableIterable<User> = service.findUsers()

    @GetMapping("/user/{userid}")
    fun getUserByUserid(@PathVariable userid: Int): User {
        try {
            return service.findByUserid(userid)
        }catch (ex: EmptyResultDataAccessException) {
            throw Exception("user with id $userid not found!")
        }
    }

    @GetMapping("/user/username/{username}")
    fun getByUsername(@PathVariable username: String): User {
        try {
            return service.findByUsername(username)
        }catch (ex: EmptyResultDataAccessException) {
            throw Exception("user with username $username not found!")
        }
    }

    @GetMapping("/login")
    fun login(@RequestBody loginInfo: Login): String {
        return service.login(loginInfo.username, loginInfo.password)
    }

    @GetMapping("/user/search/{username}")
    fun searchForUsername(@PathVariable username: String): List<User> = service.searchForUsername(username)

    @DeleteMapping("/user/{userid}")
    fun deleteUser(@PathVariable userid: Int) {
        try {
            val userToDelete = service.findByUserid(userid)
            service.deleteUser(userToDelete)
        } catch (exception: EmptyResultDataAccessException) {
            throw Exception("user with id $userid not found!")
        }
    }
}