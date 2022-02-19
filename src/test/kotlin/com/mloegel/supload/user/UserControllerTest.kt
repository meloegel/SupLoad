package com.mloegel.supload.user

import org.json.JSONObject
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [UserController::class])
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class UserControllerTest {

    @Autowired
    private lateinit var context: WebApplicationContext

    private lateinit var mvc : MockMvc

    @BeforeAll
    fun setup () {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply<DefaultMockMvcBuilder>(springSecurity())
            .build()
    }

    @Test
    fun getAllUsers() {
//        mvc.perform(
//            MockMvcRequestBuilders.get("/users")
//                .with(csrf().asHeader())
//                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .content(JSONObject().toString()))
//                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//        )
    }

    @Test
    fun getUserByUserid() {
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