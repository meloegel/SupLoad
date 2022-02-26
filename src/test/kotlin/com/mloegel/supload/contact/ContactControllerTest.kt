package com.mloegel.supload.contact

import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters


@WebFluxTest(ContactController::class)
internal class ContactControllerTest(@Autowired private val webClient: WebTestClient) {

    @MockBean
    private lateinit var mockContactService: ContactService

    private val contactList = mutableListOf<Contact>()

    private val contact = Contact(
        1, "John", "Test", "test@email.com",
        Address("123 main st", "test", "test", 55555), "555-555-5555"
    )

    private val newContact = Contact(
        50, "Jeff", "Test", "test@email.com",
        Address("456 main st", "test", "test", 55555), "555-555-5555"
    )

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
        whenever(mockContactService.findByContactid(1))
            .thenReturn(contact)
        webClient.get()
            .uri("/contact/1")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun searchContactsByFirstname() {
        whenever(mockContactService.searchContactsByFirstname("john"))
            .thenReturn(contactList)
        webClient.get()
            .uri("/contact/first/john")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun searchContactsByLastname() {
        whenever(mockContactService.searchContactsByLastname("test"))
            .thenReturn(contactList)
        webClient.get()
            .uri("/contact/last/test")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun postContact() {
        whenever(mockContactService.postContact(newContact))
            .thenReturn(newContact)
        webClient.post().uri("/contact")
            .body(BodyInserters.fromValue(newContact))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun updateContact() {
    }

    @Test
    fun deleteContact() {
    }
}