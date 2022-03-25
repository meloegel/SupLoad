package com.mloegel.supload.contact.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.pdmodel.interactive.form.PDField


class ContactUploadParser(contactPdf: PDDocument) {

    fun parseContact(contactPdf: PDDocument) {
        val pddDoc = contactPdf.documentCatalog
        val acroForm = pddDoc.acroForm
        val fields: List<PDField> = acroForm.fields

        for (x in fields) {
            println(fields)
        }
    }
}