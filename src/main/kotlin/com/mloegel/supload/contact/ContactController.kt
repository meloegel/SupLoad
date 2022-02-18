package com.mloegel.supload.contact

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ContactController(private val contactService: ContactService) {
    @GetMapping("/contacts")
    fun getAllContacts(): MutableIterable<Contact> = contactService.findAllContacts()

    @GetMapping("/contact/{contactid}")
    fun getContactById(@PathVariable contactid: Int): Contact {
        try {
           return contactService.findByContactid(contactid)
        } catch (ex: EmptyResultDataAccessException) {
            throw Exception("contact with id $contactid not found!")
        }
    }

    @GetMapping("/contact/{firsname}")
    fun findContactsByFirstname(@PathVariable firstname: String): List<Contact> = contactService.findContactsByFirstname(firstname)
}