package com.mloegel.supload.contact.pdf

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.text.PDFTextStripper
import java.io.InputStream


class ContactUploadParser {

    fun parseContact(contactPdf: InputStream): String {
        var content = ""
        PDDocument.load(contactPdf).use { it ->
            if (!it.isEncrypted) {
                content = PDFTextStripper().getText(it)
                println(content)
            }
            return content
        }
//        val acroForm = pddDoc.acroForm
//        val fields: List<PDField> = acroForm.fields
//
//        for (x in fields) {
//            println(fields)
//        }
    }

    fun load(contact: InputStream) {
        parseContact(contact)
    }
}