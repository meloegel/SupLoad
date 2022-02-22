package com.mloegel.supload.user

import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient


@WebFluxTest(UserController::class)
internal class UserControllerTest(@Autowired private val webClient: WebTestClient) {

    @MockBean
    private lateinit var mockUserService: UserService

    private val userList = mutableListOf<User>()

    @Test
    fun getAllUsers() {
        whenever(mockUserService.findAllUsers()).thenReturn(userList)
        webClient.get().uri("/users").exchange().expectStatus().isOk
    }

    @Test
    fun getUserByUserid() {
        whenever(mockUserService.findByUserid(1)).thenReturn(User(1, "admin", "password", "admin@lambdaschool.local"))
        webClient.get().uri("/user/1").exchange().expectStatus().isOk
    }

    @Test
    fun getByUsername() {
    }

    @Test
    fun login() {
    }

    @Test
    fun searchForUsername() {
    }

    @Test
    fun postUser() {
    }

    @Test
    fun updateUser() {
    }

    @Test
    fun deleteUser() {
    }
}


