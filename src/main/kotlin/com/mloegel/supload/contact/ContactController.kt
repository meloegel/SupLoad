package com.mloegel.supload.contact

import kotlinx.coroutines.reactive.awaitFirst
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.MediaType
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import java.io.ByteArrayOutputStream

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

    @PostMapping("/upload/contact", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun uploadContact(@RequestPart("contact") contact: FilePart) {
//        val contactFlow = contact.asFlow().filterIsInstance<FilePart>()
//        contactService.uploadContact(contactFlow.map { it.toBytes() })
        contactService.uploadContact(contact.toBytes())
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

    suspend fun FilePart.toBytes(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        this.content()
            .flatMap { Flux.just(it.asByteBuffer().array()) }
            .collectList()
            .awaitFirst()
            .forEach { byteStream.write(it) }

        return byteStream.toByteArray()
    }


}