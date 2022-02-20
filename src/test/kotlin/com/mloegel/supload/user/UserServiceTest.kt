package com.mloegel.supload.user

import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import com.nhaarman.mockitokotlin2.mock
import org.springframework.beans.factory.annotation.Autowired

internal class UserServiceTest {

    private val user = User(1,"test", "password", "test@lambdaschool.local")

    private val mockService = mock<UserService>()

    private val mockRepository = mock<UserRepository>()

    private val userServices = UserService(mockRepository)

    @Test
    fun findAllUsers() {
        userServices.findAllUsers()
        verify(mockRepository).findAll()
    }



    @Test
    fun findByUserid() {
        userServices.findByUserid(1)
        verify(mockRepository).findByUserid(1)
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
//        userServices.postUser(user)
//        verify(mockRepository).save(user)
    }

    @Test
    fun deleteUser() {
    }

    @Test
    fun deleteAll() {
    }
}