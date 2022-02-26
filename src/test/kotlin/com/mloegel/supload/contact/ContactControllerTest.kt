package com.mloegel.supload.contact

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.web.reactive.server.WebTestClient


@WebFluxTest(ContactController::class)
internal class ContactControllerTest(@Autowired private val webClient: WebTestClient) {

    @Test
    fun getAllContacts() {
    }

    @Test
    fun getContactById() {
    }

    @Test
    fun searchContactsByFirstname() {
    }

    @Test
    fun searchContactsByLastname() {
    }

    @Test
    fun postContact() {
    }

    @Test
    fun updateContact() {
    }

    @Test
    fun deleteContact() {
    }
}