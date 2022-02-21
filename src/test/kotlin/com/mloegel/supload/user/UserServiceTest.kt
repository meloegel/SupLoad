package com.mloegel.supload.user

import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.springframework.beans.factory.annotation.Autowired

internal class UserServiceTest {

    private val user = User(1,"test", "password", "test@lambdaschool.local")

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
        userServices.findByUsername("admin")
        verify(mockRepository).findByUsername("admin")
    }

    @Test
    fun searchForUsername() {
        userServices.searchForUsername("admin")
        verify(mockRepository).findByUsernameContainingIgnoreCase("admin")
    }

    @Test
    fun login() {
        whenever(mockRepository.findByUsername("admin")).thenReturn(User(1,"amdin", "password", "admin@lambdaschool.local"))
        userServices.login("admin", "password")
    }

    @Test
    fun postUser() {
//        userServices.deleteAll()
//        userServices.postUser(user)
//        mockRepository.save(user)
//        verify(mockRepository).findByUserid(1)
    }

    @Test
    fun deleteUser() {
        userServices.deleteUser(user)
        verify(mockRepository).delete(user)
    }
}