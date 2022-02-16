package com.mloegel.supload.user

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
    val email: String

)
