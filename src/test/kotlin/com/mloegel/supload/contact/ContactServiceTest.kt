package com.mloegel.supload.contact

import com.mloegel.supload.user.User
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class ContactServiceTest {

    private val contact =
        Contact(1, "test", "testerson", "test@email.com", "123 main st", "test", "test", "555-555-5555", 55555)

    private val mockRepository = mock<ContactRepository>()

    //    private val mockContactUploadParser = mock<ContactUploadParser>()
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
        verify(mockRepository).findContactsByFirstnameContainingIgnoreCase("test")
    }

    @Test
    fun searchContactsByLastName() {
        contactService.searchContactsByLastname("test")
        verify(mockRepository).findContactsByLastnameContainingIgnoreCase("test")
    }

    @Test
    fun findContactByUsername() {
        contactService.findContactByUsername(User(50, "testUsername", "test", "test@email.com"))
        verify(mockRepository).findContactByUser(User(50, "testUsername", "test", "test@email.com"))
    }

    @Test
    fun postContactAndCreatePdf() {
    }

    @Test
    fun uploadContact() {
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