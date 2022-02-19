package com.mloegel.supload.user

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired

internal class UserServiceTest {
    @Autowired
    val userServices: UserService? = null

    val mockService = Mockito.mock(UserService::class.java)

    val mockRepository = Mockito.mock(UserRepository::class.java)

    @Test
    fun findAllUsers() {
        userServices?.let { verify(it.findAllUsers()) }
    }

    @Test
    fun findByUserid() {
    }

    @Test
    fun findByUsername() {
    }

    @Test
    fun searchForUsername() {
    }

    @Test
    fun login() {
    }

    @Test
    fun postUser() {
    }

    @Test
    fun deleteUser() {
    }

    @Test
    fun deleteAll() {
    }
}