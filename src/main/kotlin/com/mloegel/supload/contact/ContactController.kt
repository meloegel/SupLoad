package com.mloegel.supload.contact

import com.mloegel.supload.user.UserService
import kotlinx.coroutines.reactive.awaitFirst
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.http.codec.multipart.Part
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

@RestController
class ContactController(private val contactService: ContactService, private val userService: UserService) {
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

    @GetMapping("/contact/username/{username}")
    fun findContactByUsername(@PathVariable username: String): Contact {
        val user = userService.findByUsername(username)
        return contactService.findContactByUsername(user)
    }

    @Transactional
    @PostMapping("/contact")
    fun postContact(@RequestBody contact: Contact) = contactService.postContact(contact)

    @Transactional
    @PostMapping("/contact/pdf")
    fun postContactAndCreatePdf(@RequestBody contact: Contact) = contactService.postContactAndCreatePdf(contact)

    @Transactional
    @PutMapping("/contact/{contactid}")
    fun updateContact(@PathVariable contactid: Int, @RequestBody updatedContact: Contact) {
        val updatedContactCopy = updatedContact.copy(contactid = contactid)
        contactService.postContact(updatedContactCopy)
    }

//    @PostMapping("/upload/contact", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
//    suspend fun uploadContact(
//        @RequestPart("foo") foo: String,
//        @RequestPart("bar") contact: Flux<FilePart>,
//    ) {
//        contactService.uploadContact(contact.asFlow().map { it.toBytes() })
//    }

    @Transactional
    @PostMapping("/upload/contact", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    suspend fun uploadContact(
        @RequestPart("foo") foo: String,
        @RequestPart("contact") contact: Part,
    ): ResponseEntity<Void> {
        contactService.uploadContact(contact.toBytes())
        return ResponseEntity.accepted().build()

    }

    @Transactional
    @DeleteMapping("/contact/{contactid}")
    fun deleteContact(@PathVariable contactid: Int) {
        try {
            val contactToDelete = contactService.findByContactid(contactid)
            contactService.deleteContact(contactToDelete)
        } catch (exception: EmptyResultDataAccessException) {
            throw Exception("contact with id $contactid not found!")
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteAll() = contactService.deleteAll()

//    suspend fun FilePart.toBytes(): ByteArray {
//        val byteStream = ByteArrayOutputStream()
//        this.content()
//            .flatMap { Flux.just(it.asByteBuffer().array()) }
//            .collectList()
//            .awaitFirst()
//            .forEach { byteStream.write(it) }
//
//        return byteStream.toByteArray()
//    }

    suspend fun Part.toBytes(): ByteArray {
        val byteStream = ByteArrayOutputStream()
        this.content()
            .flatMap { Flux.just(it.asByteBuffer().array()) }
            .collectList()
            .awaitFirst()
            .forEach { byteStream.write(it) }

        return byteStream.toByteArray()
    }
}