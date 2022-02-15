package com.mloegel.supload.contact

import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Entity
@Table("CONTACT")
data class ContactInformation(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var contactid: Int?,

    @Column(nullable = false)
    var firstname: String,

    @Column(nullable = false)
    var lastname: String,
)
