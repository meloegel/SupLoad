package com.mloegel.supload.user

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class UserService(val db: UserRepository) {
    fun findUsers(): MutableIterable<User> = db.findAll()

    fun findByUserid(userid: Int): User = db.findByUserid(userid)

    fun findByUsername(username:String): User = db.findByUsername(username)

    fun searchForUsername(username: String): List<User> = db.findByUsernameContainingIgnoreCase(username)

    @Transactional
    fun deleteUser(user: User) = db.delete(user)
}