package com.example.CROUD

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["com.example.CROUD"])
@EnableFeignClients
class CroudApplication

fun main(args: Array<String>) {
    runApplication<CroudApplication>(*args)

}
