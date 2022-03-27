package com.mloegel.supload

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SuploadApplication

fun main(args: Array<String>) {
    runApplication<SuploadApplication>(*args)
}
