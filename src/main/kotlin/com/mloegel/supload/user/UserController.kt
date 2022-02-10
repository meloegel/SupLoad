package com.mloegel.supload.user

import org.springframework.web.bind.annotation.GetMapping

class UserController(val service: UserService) {
    @GetMapping("/users")
    fun getAllUsers(): MutableIterable<User> = service.findUsers()
}