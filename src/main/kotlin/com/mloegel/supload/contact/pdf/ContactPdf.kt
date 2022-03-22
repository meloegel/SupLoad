package com.mloegel.supload.contact.pdf

import com.mloegel.supload.contact.Contact
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Entity
@Table("CONTACTPDF")
data class ContactPdf(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val contactpdfid: Int?,

    @Column(nullable = false)
    val contactpdf: ByteArray,

    @OneToOne
    @JoinColumn(name = "contactid", nullable = false)
    var contact: Contact
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ContactPdf

        if (contactpdfid != other.contactpdfid) return false
        if (!contactpdf.contentEquals(other.contactpdf)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = contactpdfid ?: 0
        result = 31 * result + contactpdf.contentHashCode()
        return result
    }
}
