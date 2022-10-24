package com.poc.ktor

import com.poc.ktor.plugins.configureRouting
import com.poc.ktor.plugins.configureSerialization
import com.poc.ktor.plugins.configureSockets
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.LoggerFactory

val globalLogger = LoggerFactory.getLogger("GlobalLogger")
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureSerialization()
        configureSockets()
        configureRouting()
    }.start(wait = true)
}
