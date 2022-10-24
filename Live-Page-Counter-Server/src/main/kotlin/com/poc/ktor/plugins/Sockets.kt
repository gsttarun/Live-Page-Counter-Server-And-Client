package com.poc.ktor.plugins

import MessageType
import WSMessage
import com.google.gson.Gson
import com.poc.ktor.LivePageCount
import com.poc.ktor.globalLogger
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import java.time.Duration

fun Application.configureSockets() {
    install(WebSockets) {
        pingPeriod = Duration.ofSeconds(15)
        timeout = Duration.ofSeconds(15)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }

    routing {
        webSocket("/ws") { // websocketSession
            for (frame in incoming) {
                if (frame is Frame.Text) {
                    val message = Gson().fromJson(frame.readText(), WSMessage::class.java)
//                    val text = frame.readText()
//                    outgoing.send(Frame.Text("YOU SAID: $text"))
                    when (message.type) {
                        MessageType.PAGE_VISIT -> {
                            LivePageCount.incrementCount()
                            updateCountToClients()
                        }

                        MessageType.PAGE_EXIT -> {
                            LivePageCount.decrementCount()
                            updateCountToClients()
                        }

                        MessageType.BYE -> {

                            close(CloseReason(CloseReason.Codes.NORMAL, "Client said BYE"))
                            globalLogger.info("Client normal closure - Client said BYE")
                        }
                    }
                }
            }
        }
    }
}

private suspend fun DefaultWebSocketServerSession.updateCountToClients() {
    globalLogger.info("Page Count -> ${LivePageCount.getLiveCount()}")
    outgoing.send(Frame.Text("Count-${LivePageCount.getLiveCount()}"))
}
