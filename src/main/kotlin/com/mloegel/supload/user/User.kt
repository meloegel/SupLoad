package com.mloegel.supload.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.mloegel.supload.contact.Contact
import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Entity
@Table("USERS")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val userid: Int?,

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], orphanRemoval = true)
    @JsonIgnoreProperties(value = ["user"], allowSetters = true)
    var contact: Contact? = null
)
