package com.mloegel.supload.contact

import org.springframework.data.repository.CrudRepository

interface ContactRepository: CrudRepository<Contact, String> {
    fun findByContactid(contactid: Int): Contact

    fun findContactsByFirstname(firstname: String): List<Contact>

    fun findContactsByLastname(lastname: String): List<Contact>
}