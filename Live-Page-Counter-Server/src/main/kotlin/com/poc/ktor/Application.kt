package com.poc.ktor

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.poc.ktor.plugins.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureSockets()
        configureRouting()
    }.start(wait = true)
}
