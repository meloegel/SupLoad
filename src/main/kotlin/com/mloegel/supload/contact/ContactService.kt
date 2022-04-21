package com.mloegel.supload.contact

import com.mloegel.supload.contact.pdf.ContactUploadParser
import com.mloegel.supload.contact.pdf.PdfGenerator
import com.mloegel.supload.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.io.ByteArrayInputStream
import kotlin.io.path.createTempFile
import kotlin.io.path.pathString
import kotlin.io.path.writeBytes


@Transactional
@Service
class ContactService(
    private val db: ContactRepository,
//    @Autowired val contactUploadParser: ContactUploadParser
) {
    private val contactUploadParser = ContactUploadParser()

    fun findAllContacts(): MutableIterable<Contact> = db.findAll()

    fun findByContactid(contactid: Int): Contact = db.findByContactid(contactid)

    fun searchContactsByFirstname(firstname: String): List<Contact> =
        db.findContactsByFirstnameContainingIgnoreCase(firstname)

    fun searchContactsByLastname(lastname: String): List<Contact> =
        db.findContactsByLastnameContainingIgnoreCase(lastname)

    fun findContactByUsername(user: User) =
        db.findContactByUser(user)

    @Transactional
    fun postContactAndCreatePdf(contact: Contact) {
        val pdfGenerator = PdfGenerator(contact)
        val contactPdf = pdfGenerator.generate()
        db.save(contact)
        createTempFile().apply {
            writeBytes(contactPdf.toByteArray())
            println("file://${this.pathString}")
        }
        // explorer.exe "file://filePath" to open temp files
    }

    @Transactional
    fun postContact(contact: Contact) {
        db.save(contact)
    }

    suspend fun uploadContact(contact: Flow<ByteArray>) {
        contactUploadParser.load(ByteArrayInputStream(contact.first()))
    }

    @Transactional
    fun deleteContact(contact: Contact) = db.delete(contact)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteAll() = db.deleteAll()
}