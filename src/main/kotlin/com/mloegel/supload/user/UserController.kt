package com.mloegel.supload.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

class UserController(val service: UserService) {
    @GetMapping("/users")
    fun getAllUsers(): MutableIterable<User> = service.findUsers()

    @GetMapping("/user/{userid}")
    fun getUserByUserid(@PathVariable userid: Int): User = service.findByUserid(userid)

    @GetMapping("/user/username/{username}")
    fun getByUsername(@PathVariable username: String): User = service.findByUsername(username)

    @GetMapping("/user/search/{username}")
    fun searchForUsername(@PathVariable username: String): List<User> = service.searchForUsername(username)
}