package com.mloegel.supload.contact

import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, String> {
    fun findByContactid(contactid: Int): Contact

    fun findContactsByFirstnameContainingIgnoreCase(firstname: String): List<Contact>

    fun findContactsByLastnameContainingIgnoreCase(lastname: String): List<Contact>
}