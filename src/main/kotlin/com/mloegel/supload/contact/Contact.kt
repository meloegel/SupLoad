package com.mloegel.supload.contact

import com.mloegel.supload.user.User
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

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false)
    val phone: String,

    @Column(nullable = false)
    val street: String,

    @Column(nullable = false)
    val city: String,

    @Column(nullable = false)
    val state: String,

    @Column(nullable = false)
    val zip: Int,

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    var user: User? = null
)
