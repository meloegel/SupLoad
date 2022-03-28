package com.mloegel.supload.contact

import com.mloegel.supload.contact.pdf.ContactUploadParser
import com.mloegel.supload.contact.pdf.PdfGenerator
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
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

    suspend fun uploadContact(contact: ByteArray) {
        val inputStream = ByteArrayInputStream(contact)
        contactUploadParser.load(inputStream)
    }

    @Transactional
    fun deleteContact(contact: Contact) = db.delete(contact)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    fun deleteAll() = db.deleteAll()

    private suspend fun getInputStreamFromFluxDataBuffer(data: Flux<DataBuffer>): InputStream {
        val osPipe = PipedOutputStream()
        val isPipe = PipedInputStream(osPipe)
        DataBufferUtils.write(data, osPipe)
            .subscribeOn(Schedulers.boundedElastic())
            .doOnComplete { osPipe.close() }
            .subscribe(DataBufferUtils.releaseConsumer())
        return isPipe
    }
}