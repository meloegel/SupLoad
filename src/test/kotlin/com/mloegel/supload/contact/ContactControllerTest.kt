package com.mloegel.supload.contact

import com.mloegel.supload.user.User
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

    private val user = User(50, "testUsername", "test", "test@email.com")

    private val contact = Contact(
        1, "John", "Test", "test@email.com",
        "123 main st", "test", "test", "555-555-5555", 55555, user
    )


    private val otherContact = Contact(
        50, "Jeff", "Test", "test@email.com",
        "456 main st", "test", "test", "555-555-5555", 55555
    )

    private val newContact = NewContact(
        "Jeff", "Test", "test@email.com",
        "456 main st", "test", "test", "555-555-5555", 55555
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
    fun findContactByUsername() {
        whenever(mockContactService.findContactByUsername(user)).thenReturn(contact)
        webClient.get()
            .uri("/contact/testUsername")
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun postContact() {
        webClient.post().uri("/contact")
            .body(BodyInserters.fromValue(newContact))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun updateContact() {
        webClient.put().uri("/contact/50")
            .body(BodyInserters.fromValue(otherContact))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun deleteContact() {
        whenever(mockContactService.findByContactid(50)).thenReturn(otherContact)
        webClient.delete().uri("/contact/50")
            .exchange()
            .expectStatus().isOk
    }
}