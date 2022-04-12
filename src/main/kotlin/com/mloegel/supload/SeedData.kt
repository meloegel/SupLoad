package com.mloegel.supload


import com.mloegel.supload.contact.Contact
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


        val u1 = User(1, "admin", "password", "admin@lambdaschool.local")
        userService?.postUser(u1)

        val u2 = User(2, "cinnamon", "1234567", "cinnamon@lambdaschool.local")
        userService?.postUser(u2)

        val u3 = User(3, "barnbarn", "ILuvM4th!", "barnbarn@lambdaschool.local")
        userService?.postUser(u3)

        val u4 = User(4, "puttat", "password", "puttat@school.lambda")
        userService?.postUser(u4)

        val u5 = User(5, "misskitty", "password", "misskitty@school.lambda")
        userService?.postUser(u5)

        val u6 = User(6, "fart", "password", "email@email.com")
        userService?.postUser(u6)


        val c1 =
            Contact(1, "Bill", "Smith", "bill@email.com", "123 Main St", "Detroit", "MI", "555-555-55550", 12345, u1)
        contactService?.postContact(c1)

        val c2 =
            Contact(
                2,
                "Ted",
                "Potter",
                "ted@email.com",
                "345 Main St",
                "San Fransisco",
                "CA",
                "555-555-5555",
                12345,
                u2
            )
        contactService?.postContact(c2)

        val c3 =
            Contact(3, "Fred", "Johnson", "fred@email.com", "789 Main St", "Chicago", "IL", "555-555-5555", 12345, u3)
        contactService?.postContact(c3)

        val c4 =
            Contact(4, "Steve", "Rogers", "steve@email.com", "1414 Main St", "Gary", "IN", "555-555-5555", 12345, u4)
        contactService?.postContact(c4)

        val c5 =
            Contact(5, "Taco", "Smith", "taco@email.com", "452 Main St", "Atlanta", "GA", "555-555-5555", 12345, u5)
        contactService?.postContact(c5)

        val c6 = Contact(6, "Zeus", "God", "zeus@email.com", "6548 Main St", "Miami", "FL", "555-555-5555", 12345, u6)
        contactService?.postContact(c6)

    }
}