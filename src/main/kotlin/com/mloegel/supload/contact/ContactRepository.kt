package com.mloegel.supload.contact

import com.mloegel.supload.user.User
import org.springframework.data.repository.CrudRepository

interface ContactRepository : CrudRepository<Contact, String> {

    fun findByContactid(contactid: Int): Contact

    fun findContactsByFirstnameContainingIgnoreCase(firstname: String): List<Contact>

    fun findContactsByLastnameContainingIgnoreCase(lastname: String): List<Contact>

    fun findContactByUser(user: User): Contact
}