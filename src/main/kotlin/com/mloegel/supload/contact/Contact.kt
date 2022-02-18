package com.mloegel.supload.contact

import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Entity
@Table("CONTACT")
data class Contact(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val contactid: Int?,

    @Column(nullable = false)
    val firstname: String,

    @Column(nullable = false)
    val lastname: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    val address: Address,

    @Column(nullable = false)
    val phone: String,
)

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zip: Int,
        )