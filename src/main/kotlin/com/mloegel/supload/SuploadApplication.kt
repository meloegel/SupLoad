package com.mloegel.supload

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SuploadApplication

fun main(args: Array<String>) {
	runApplication<SuploadApplication>(*args)
}
