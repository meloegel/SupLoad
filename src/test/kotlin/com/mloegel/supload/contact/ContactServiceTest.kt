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
    }

    @Test
    fun searchContactsByFirstname() {
    }

    @Test
    fun searchContactsByLastName() {
    }

    @Test
    fun postContact() {
    }

    @Test
    fun deleteContact() {
    }
}