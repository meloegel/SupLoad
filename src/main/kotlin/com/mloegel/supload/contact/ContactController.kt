package com.mloegel.supload.contact

import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.web.bind.annotation.*

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

    @GetMapping("/contact/first/{firsname}")
    fun searchContactsByFirstname(@PathVariable firstname: String): List<Contact> {
        return contactService.searchContactsByFirstname(firstname)
    }

    @GetMapping("/contact/last/{lastname}")
    fun searchContactsByLastname(@PathVariable lastname: String): List<Contact> {
        return contactService.searchContactsByLastName(lastname)
    }

    @PostMapping("/contact")
    fun postContact(@RequestBody contact: Contact) = contactService.postContact(contact)

    @PutMapping("/contact/{contactid}")
    fun updateContact(@PathVariable contactid: Int, @RequestBody updatedContact: Contact) {
        val updatedContactCopy = updatedContact.copy(contactid = contactid)
        contactService.postContact(updatedContactCopy)
    }

    @DeleteMapping("/contact/{contactid}")
    fun deleteContact(@PathVariable contactid: Int) {
        try {
            val contactToDelete = contactService.findByContactid(contactid)
            contactService.deleteContact(contactToDelete)
        } catch (exception: EmptyResultDataAccessException) {
            throw Exception("contact with id $contactid not found!")
        }
    }

}