package app.wyvern.server.gateway

import app.wyvern.common.packets.abstract.PacketData
import app.wyvern.server.gateway.util.InternalCallable
import app.wyvern.server.gateway.util.RegularCallable
import app.wyvern.server.gateway.util.WebsocketCallable
import app.wyvern.server.plugins.forEachUnblocking
import io.ktor.websocket.*

object HandlerRegistry {
    val handlers =
        mutableListOf<Pair<Any, InternalCallable>>()

    inline fun <reified T : PacketData> register(handler: RegularCallable<T>) {
        handlers.add(T::class to handler)
    }

    inline fun <reified T : PacketData> register(handler: WebsocketCallable<T>) {
        handlers.add(T::class to handler)
    }
    inline fun <reified T : PacketData> onReceive(packet: T, webSocketSession: WebSocketSession) {
        val handlers_ = handlers.filter { it.first == T::class }
        handlers_.forEachUnblocking {
            @Suppress("UNCHECKED_CAST")
            when (it.second) {
                is WebsocketCallable<*> -> {
                    (it.second as WebsocketCallable<T>).invoke(packet, webSocketSession)
                }
                is RegularCallable<*> -> {
                    (it.second as RegularCallable<T>).invoke(packet)
                }
            }
        }
    }
}