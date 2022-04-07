package app.wyvern.server.plugins

import io.ktor.server.application.*
import io.ktor.server.locations.*
import io.ktor.server.plugins.*

fun Application.configureRouting() {
    install(AutoHeadResponse)
    install(Locations) {
    }
}
