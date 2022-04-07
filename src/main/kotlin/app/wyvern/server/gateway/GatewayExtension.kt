package app.wyvern.server.gateway

import app.wyvern.common.packets.HelloPacket
import app.wyvern.common.packets.errors.GatewayErrors
import app.wyvern.server.gateway.contexts.on
import app.wyvern.server.plugins.openAsConnection
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*

fun Application.implementGateway() {
    routing {
        webSocket("/gateway") {
            openAsConnection {
                // first, send a hello packet
                send(HelloPacket(
                    platform = "kotlin/jvm",
                    version = "0.0.1"
                ))
                on<HelloPacket> { it ->
                    if (hasSentHello) {
                        send(GatewayErrors.ALREADY_HELLO)
                        return@on
                    }
                    val d = it.data
                    println(
                        "A client just connected to gateway on ${d.platform} v${d.version}"
                    )
                    hasSentHello = true
                }


            }
        }
    }
}
