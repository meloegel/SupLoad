package com.mloegel.supload.contact

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class ContactServiceTest {

    private val contact =
        Contact(1, "test", "testerson", "test@email.com", Address("123 main st", "test", "test", 55555), "555-555-5555")

    private val mockRepository = mock<ContactRepository>()

    private val contactService = ContactService(mockRepository)

    @Test
    fun findAllContacts() {
        contactService.findAllContacts()
        verify(mockRepository).findAll()
    }

    @Test
    fun findByContactid() {
        contactService.findByContactid(1)
        verify(mockRepository).findByContactid(1)
    }

    @Test
    fun searchContactsByFirstname() {
        contactService.searchContactsByFirstname("test")
        verify(mockRepository).findContactsByFirstname("test")
    }

    @Test
    fun searchContactsByLastName() {
        contactService.searchContactsByLastName("test")
        verify(mockRepository).findContactsByLastname("test")
    }

    @Test
    fun postContact() {
//        contactService.postContact(contact)
//        verify(mockRepository).save(contact)
    }

    @Test
    fun deleteContact() {
        contactService.deleteContact(contact)
        verify(mockRepository).delete(contact)
    }
}