package com.mloegel.supload.user

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

data class Login(
    val username: String,
    val password: String
)

@RestController
class UserController(private val service: UserService) {
    @GetMapping("/users")
    fun getAllUsers(): MutableIterable<User> = service.findAllUsers()

    @GetMapping("/user/{userid}")
    fun getUserByUserid(@PathVariable userid: Int): User {
        try {
            return service.findByUserid(userid)
        } catch (ex: EmptyResultDataAccessException) {
            throw Exception("user with id $userid not found!")
        }
    }

    @GetMapping("/user/username/{username}")
    fun getByUsername(@PathVariable username: String): User {
        try {
            return service.findByUsername(username)
        } catch (ex: EmptyResultDataAccessException) {
            throw Exception("user with username $username not found!")
        }
    }

    @GetMapping("/user/search/{username}")
    fun searchForUsername(@PathVariable username: String): List<User> = service.searchForUsername(username)

    @PostMapping("/login", produces = [MediaType.APPLICATION_JSON_VALUE])
    fun login(@RequestBody loginInfo: Login): ResponseEntity<String> {
        return try {
            ResponseEntity<String>(service.login(loginInfo.username, loginInfo.password), HttpStatus.OK)
        } catch (ex: IllegalArgumentException) {
            ResponseEntity<String>(HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/user")
    fun postUser(@RequestBody user: User) = service.postUser(user)

    @PutMapping("/user/{userid}")
    fun updateUser(@PathVariable userid: Int, @RequestBody updatedUser: User) {
        val updatedUserCopy = updatedUser.copy(userid = userid)
        service.postUser(updatedUserCopy)
    }

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