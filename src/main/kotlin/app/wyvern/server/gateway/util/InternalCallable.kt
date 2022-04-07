package app.wyvern.server.gateway.util

import io.ktor.websocket.*

sealed interface InternalCallable

fun interface WebsocketCallable<T> : InternalCallable {
    fun invoke(arg: T, arg2: WebSocketSession)
}

fun interface RegularCallable<T> : InternalCallable {
    fun invoke(arg: T)
}