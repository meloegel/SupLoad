package com.mloegel.supload.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(val db: UserRepository) {
    fun findAllUsers(): MutableIterable<User> = db.findAll()

    fun findByUserid(userid: Int): User = db.findByUserid(userid)

    fun findByUsername(username:String): User = db.findByUsername(username)

    fun searchForUsername(username: String): List<User> = db.findByUsernameContainingIgnoreCase(username)

    fun login(username: String, password: String): String {
        val userToLogin = db.findByUsername(username)
        return if (username == userToLogin.username && password == userToLogin.password) {
            "token here"
        } else {
            "Username and password did not match"
        }
    }

    @Transactional
    fun postUser(user: User) = db.save(user)

    @Transactional
    fun deleteUser(user: User) = db.delete(user)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteAll() = db.deleteAll()
}