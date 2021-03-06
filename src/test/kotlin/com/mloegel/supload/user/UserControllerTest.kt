package com.mloegel.supload.user

import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters


@WebFluxTest(UserController::class)
internal class UserControllerTest(@Autowired private val webClient: WebTestClient) {

    @MockBean
    private lateinit var mockUserService: UserService

    private val userList = mutableListOf<User>()

    private val loginInfo = Login("admin", "password")

    private val loginInfoBad = Login("admin", "fart")

    private val newUser = User(50, "testUsername", "password", "test@email.com")

    @Test
    fun getAllUsers() {
        whenever(mockUserService.findAllUsers())
            .thenReturn(userList)
        webClient.get()
            .uri("/users")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun getUserByUserid() {
        whenever(mockUserService.findByUserid(1))
            .thenReturn(User(1, "admin", "password", "email@test.com"))
        webClient.get()
            .uri("/user/1")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `userid does not exist`() {
        whenever(mockUserService.findByUserid(123424234))
            .thenThrow(EmptyResultDataAccessException(1))
        webClient.get()
            .uri("/user/123424234")
            .exchange()
            .expectStatus().is5xxServerError
    }

    @Test
    fun getByUsername() {
        whenever(mockUserService.findByUsername("admin")).thenReturn(
            User(
                1,
                "admin",
                "password",
                "email@test.com"
            )
        )
        webClient.get().uri("/user/username/admin")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun searchForUsername() {
        whenever(mockUserService.searchForUsername("admin")).thenReturn(userList)
        webClient.get().uri("/user/username/admin")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun login() {
        whenever(mockUserService.login("admin", "password")).thenReturn("Token here")
        whenever(mockUserService.findByUsername("admin")).thenReturn(
            User(
                1,
                "admin",
                "password",
                "email@test.com"
            )
        )
        webClient.post().uri("/login")
            .body(BodyInserters.fromValue(loginInfo))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun `bad password`() {
        whenever(mockUserService.login("admin", "password")).thenReturn("Username and password did not match")
        whenever(mockUserService.findByUsername("admin")).thenReturn(
            User(
                1,
                "admin",
                "password",
                "email@test.com"
            )
        )
        webClient.post().uri("/login")
            .body(BodyInserters.fromValue(loginInfoBad))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun postUser() {
        whenever(mockUserService.postUser(newUser)).thenReturn(newUser)
        webClient.post().uri("/user")
            .body(BodyInserters.fromValue(newUser))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun updateUser() {
        whenever(mockUserService.postUser(newUser)).thenReturn(newUser)
        webClient.put().uri("/user/50")
            .body(BodyInserters.fromValue(newUser))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun deleteUser() {
        whenever(mockUserService.findByUserid(1)).thenReturn(newUser)
        webClient.delete().uri("/user/50")
            .exchange()
            .expectStatus().isOk
    }
}


