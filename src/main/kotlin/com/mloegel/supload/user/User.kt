package com.mloegel.supload.user

import org.springframework.data.relational.core.mapping.Table
import javax.persistence.*

@Entity
@Table("USERS")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var userid: Int?,

    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var password: String,

    @Column(nullable = false, unique = true)
    var email: String

)
