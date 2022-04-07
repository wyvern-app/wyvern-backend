package app.wyvern

import app.wyvern.server.gateway.implementGateway
import app.wyvern.server.plugins.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*

fun main() {

    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        configureRouting()
        configureSecurity()
        configureHTTP()
        configureSerialization()
        configureSockets()

        routing {
            route("/api"){
                route("/v1") {
                    implementGateway() // gateway websocket :>

                }
            }
        }
    }
        .start(wait = true)
}

