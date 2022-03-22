package com.mloegel.supload.contact

import com.mloegel.supload.contact.pdf.PdfGenerator
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ContactService(val db: ContactRepository) {
    fun findAllContacts(): MutableIterable<Contact> = db.findAll()

    fun findByContactid(contactid: Int): Contact = db.findByContactid(contactid)

    fun searchContactsByFirstname(firstname: String): List<Contact> =
        db.findContactsByFirstnameContainingIgnoreCase(firstname)

    fun searchContactsByLastname(lastname: String): List<Contact> =
        db.findContactsByLastnameContainingIgnoreCase(lastname)

    @Transactional
    fun postContact(contact: Contact) {
        val pdfGenerator = PdfGenerator(contact)
        val contactPdf = pdfGenerator.generate()
        db.save(contact)
        println(contactPdf)
    }

    @Transactional
    fun deleteContact(contact: Contact) = db.delete(contact)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteAll() = db.deleteAll()

}