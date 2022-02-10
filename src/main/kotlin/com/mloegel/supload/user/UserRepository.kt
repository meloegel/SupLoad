package com.mloegel.supload.user

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, String> {
}