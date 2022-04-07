package app.wyvern.server.gateway.contexts

import app.wyvern.common.packets.abstract.PacketData
import app.wyvern.server.gateway.HandlerRegistry
import io.ktor.websocket.*


inline fun <reified T : PacketData> ConnectionContext.on(
    noinline handler: (T) -> Unit
) {
    HandlerRegistry.register(handler)
}
inline fun <reified T : PacketData> ConnectionContext.on(
    noinline handler: (T, WebSocketSession) -> Unit
) {
    HandlerRegistry.register(handler)
}