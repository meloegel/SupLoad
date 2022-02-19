package com.mloegel.supload


import com.mloegel.supload.contact.ContactService
import com.mloegel.supload.user.User
import com.mloegel.supload.user.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional


@Transactional
@ConditionalOnProperty(prefix = "command.line.runner", value = ["enabled"], havingValue = "true", matchIfMissing = true)
@Component
class SeedData : CommandLineRunner {
    @Autowired
    var userService: UserService? = null

    @Autowired
    var contactService: ContactService? = null


    @Transactional
    @Throws(Exception::class)
    override fun run(args: Array<String?>?) {
        userService?.deleteAll()
        contactService?.deleteAll()

        val u1 = User(1,"admin", "password", "admin@lambdaschool.local")
        userService?.postUser(u1)

        val u2 = User(6, "cinnamon", "1234567", "cinnamon@lambdaschool.local")
        userService?.postUser(u2)

        val u3 = User(10, "barnbarn", "ILuvM4th!", "barnbarn@lambdaschool.local")
        userService?.postUser(u3)

        val u4 = User(15, "puttat", "password", "puttat@school.lambda")
        userService?.postUser(u4)

        val u5 = User( 17, "misskitty", "password", "misskitty@school.lambda")
        userService?.postUser(u5)

        val u6 = User(18,"fart", "password", "email@email.com")
        userService?.postUser(u6)
    }
}