package com.mloegel.supload.contact.pdf

import com.mloegel.supload.contact.Contact
import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm
import org.springframework.util.ResourceUtils
import java.io.ByteArrayOutputStream


class PdfGenerator(private val contact: Contact) {

    fun generate(): ByteArrayOutputStream {
        PDDocument.load(ResourceUtils.getFile("classpath:pdf-template/fillableContact.pdf"))
            .use { document ->
                document.documentCatalog.acroForm.apply {
                    fillContact(this, contact)
                    flatten()
                }
                val baos = ByteArrayOutputStream()
                document.save(baos)
                return baos
            }
    }

    private fun fillContact(acroForm: PDAcroForm, contact: Contact) {
        fill(acroForm, "firstname", contact.firstname)
        fill(acroForm, "lastname", contact.lastname)
        fill(acroForm, "email", contact.email)
        fill(acroForm, "phone", contact.phone)
        fill(acroForm, "street", contact.street)
        fill(acroForm, "city", contact.city)
        fill(acroForm, "state", contact.state)
        fill(acroForm, "zip", contact.zip)
    }

    private fun fill(acroForm: PDAcroForm, field: String, value: Int) {
        fill(acroForm, field, value.toString())
    }

    private fun fill(acroForm: PDAcroForm, field: String, value: String?) {
        value.let { acroForm.getField(field).setValue(value) }
    }
}