package com.mloegel.supload.contact

import org.apache.pdfbox.pdmodel.PDDocument
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

    @GetMapping("/contact/first/{firstname}")
    fun searchContactsByFirstname(@PathVariable firstname: String): List<Contact> {
        return contactService.searchContactsByFirstname(firstname)
    }

    @GetMapping("/contact/last/{lastname}")
    fun searchContactsByLastname(@PathVariable lastname: String): List<Contact> {
        return contactService.searchContactsByLastname(lastname)
    }

    @PostMapping("/contact")
    fun postContact(@RequestBody contact: Contact) = contactService.postContact(contact)

    @PostMapping("/contact/pdf")
    fun postContactAndCreatePdf(@RequestBody contact: Contact) = contactService.postContactAndCreatePdf(contact)

    @PutMapping("/contact/{contactid}")
    fun updateContact(@PathVariable contactid: Int, @RequestBody updatedContact: Contact) {
        val updatedContactCopy = updatedContact.copy(contactid = contactid)
        contactService.postContact(updatedContactCopy)
    }

    @PostMapping("/upload/contact")
    fun uploadContact(@RequestBody contact: PDDocument) = contactService.uploadContact(contact)

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