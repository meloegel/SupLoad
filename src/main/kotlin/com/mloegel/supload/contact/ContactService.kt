package com.mloegel.supload.contact

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@Service
class ContactService(val db: ContactRepository) {
    fun findAllContacts(): MutableIterable<Contact> = db.findAll()

    fun findByContactid(contactid: Int): Contact = db.findByContactid(contactid)

    fun findContactsByFirstname(firstname:String): List<Contact> = db.findContactsByFirstname(firstname)

    fun findContactsByLastName(lastname: String): List<Contact> = db.findContactsByLastname(lastname)
}