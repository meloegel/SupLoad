package com.mloegel.supload.contact

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Test

internal class ContactServiceTest {

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
    }

    @Test
    fun deleteContact() {
    }
}