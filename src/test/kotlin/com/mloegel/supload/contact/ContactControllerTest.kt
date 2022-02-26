package com.mloegel.supload.contact

import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient


@WebFluxTest(ContactController::class)
internal class ContactControllerTest(@Autowired private val webClient: WebTestClient) {

    @MockBean
    private lateinit var mockContactService: ContactService

    private val contactList = mutableListOf<Contact>()

    @Test
    fun getAllContacts() {
        whenever(mockContactService.findAllContacts())
            .thenReturn(contactList)
        webClient.get()
            .uri("/contacts")
            .exchange()
            .expectStatus().isOk
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