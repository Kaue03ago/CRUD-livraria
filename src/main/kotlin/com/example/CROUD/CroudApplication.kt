package com.example.CROUD

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

//@SpringBootApplication
@SpringBootApplication(scanBasePackages = ["com.example.CROUD"])
class CroudApplication

fun main(args: Array<String>) {
	runApplication<CroudApplication>(*args)

}
